package com.gs.lshly.biz.support.merchant.service.pos.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.merchant.entity.Merchant;
import com.gs.lshly.biz.support.merchant.entity.MerchantAccount;
import com.gs.lshly.biz.support.merchant.entity.MerchantApply;
import com.gs.lshly.biz.support.merchant.enums.MerchantAccountStateEnum;
import com.gs.lshly.biz.support.merchant.enums.MerchantAccountTypeEnum;
import com.gs.lshly.biz.support.merchant.enums.MerchantApplyStateEnum;
import com.gs.lshly.biz.support.merchant.repository.IMerchantAccountRepository;
import com.gs.lshly.biz.support.merchant.repository.IMerchantApplyRepository;
import com.gs.lshly.biz.support.merchant.repository.IShopRepository;
import com.gs.lshly.biz.support.merchant.service.pos.IPosShopService;
import com.gs.lshly.common.enums.*;
import com.gs.lshly.common.enums.merchant.LegalPersonTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.foundation.vo.PicturesVO;
import com.gs.lshly.common.struct.pos.dto.PosShopDTO;
import com.gs.lshly.common.struct.pos.dto.PosShopRequestDTO;
import com.gs.lshly.common.struct.pos.qto.PosShopQTO;
import com.gs.lshly.common.utils.BASE64DecodedMultipartFileUtil;
import com.gs.lshly.common.utils.PwdUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.middleware.oss.service.IFileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-13
*/
@Component
public class PosShopServiceImpl implements IPosShopService {

    @Autowired
    private IShopRepository repository;
    @Autowired
    private IMerchantAccountRepository merchantAccountRepository;
    @Autowired
    private IMerchantApplyRepository merchantApplyRepository;
    @Autowired
    private IFileService fileService;


    private String pwd = "123456";


    @Override
    public ResponseData<Void> pullListShop(PosShopQTO.QTO qto) {
        return null;
    }

    @Override
    public ResponseData<Void> pullShopDetails(PosShopDTO.IdDTO dto) {
        return null;
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public void synchroPosShop(PosShopRequestDTO.DTO dto) {
        //校验数据
        checkData(dto);

        //判断该手机号是否已被注册
        QueryWrapper<MerchantApply> applyWrapper = MybatisPlusUtil.query();
        applyWrapper.eq("pos_shop_id",dto.getPosCode());
        MerchantApply apply = merchantApplyRepository.getOne(applyWrapper);

        QueryWrapper<MerchantAccount> merchantAccountQueryWrapper = MybatisPlusUtil.query();
        merchantAccountQueryWrapper.eq("user_name",dto.getAdminPhone());
        MerchantAccount account =  merchantAccountRepository.getOne(merchantAccountQueryWrapper);


        if(ObjectUtils.isEmpty(apply) && ObjectUtils.isNotEmpty(account)){
            throw new BusinessException("该手机号已被注册！");
        }
        if (ObjectUtils.isNotEmpty(apply) && ObjectUtils.isNotEmpty(account) && (!apply.getAccountId().equals(account.getId()))){
            throw new BusinessException("该手机号已被注册！");
        }
        if (ObjectUtils.isEmpty(apply) && ObjectUtils.isEmpty(account)){
            //注册成功生成主帐号(所有的入驻信息是关联在这个主帐号下),平台审核的时候为帐号分配商家,审核开通店铺的时候创建详细的商品分类数据,和企业字点
            MerchantAccount merchantAccount = new MerchantAccount();
            merchantAccount.setUserName(dto.getAdminPhone());
            merchantAccount.setPhone(dto.getAdminPhone());
            merchantAccount.setAccountType(MerchantAccountTypeEnum.主帐号.getCode());
            merchantAccount.setTerminal(TerminalEnum.BBB.getCode());
            merchantAccount.setAccountState(MerchantAccountStateEnum.启用.getCode());
            merchantAccount.setUserPwd(PwdUtil.encode(pwd));
            merchantAccountRepository.save(merchantAccount);

            //同步入驻信息
            MerchantApply merchantApply = new MerchantApply();
            merchantApply.setLegalType(LegalTypeEnum.企业.getCode());
            merchantApply.setCorpLicenseNum(dto.getUnifiedSocialCreditCode());
            merchantApply.setPersonName(dto.getPosLegalPerson());
            merchantApply.setPersonIdcardType(dto.getPosLegalPersonType());
            merchantApply.setPersonIdcardNo(dto.getPosLegalPersonIdcard());
            merchantApply.setShopName(dto.getPosName());
            merchantApply.setPosShopId(dto.getPosCode());
            merchantApply.setAccountId(merchantAccount.getId());
            merchantApply.setShopManName(dto.getAdminName());
            merchantApply.setShopManPhone(dto.getAdminPhone());
            merchantApply.setShopAddress(dto.getPosAddress());
            merchantApply.setShopLatitude(StringUtils.isBlank(dto.getLat())?BigDecimal.ZERO:new BigDecimal(dto.getLat()));
            merchantApply.setShopLongitude(StringUtils.isBlank(dto.getLont())?BigDecimal.ZERO:new BigDecimal(dto.getLont()));
            merchantApply.setState(MerchantApplyStateEnum.待审.getCode());
            merchantApply.setShopManEmail(StringUtils.isBlank(dto.getAdminEmail())?"":dto.getAdminEmail());
            merchantApply.setProgress(MerchantApplyProgressEnum.进度信息.getCode());
            // 将base64码解析并上传到oss
            Map<String,String> imgs = getImageBase64(dto.getPosLegalPersonIdcardImgs());
            if (ObjectUtils.isNotEmpty(imgs)){
                merchantApply.setPersonIdcardBack(StringUtils.isBlank(imgs.get("back"))?"":imgs.get("back"));
                merchantApply.setPersonIdcardFront(StringUtils.isBlank(imgs.get("front"))?"":imgs.get("front"));
            }
            merchantApplyRepository.save(merchantApply);
        }
        if (ObjectUtils.isNotEmpty(apply) && ObjectUtils.isNotEmpty(account) && apply.getAccountId().equals(account.getId())){
            //更新
            if ((ObjectUtils.isEmpty(apply.getIsOpen()) || apply.getIsOpen().equals(TrueFalseEnum.否.getCode())) ){
                MerchantApply merchantApply = new MerchantApply();
                merchantApply.setId(apply.getId());
                merchantApply.setCorpLicenseNum(dto.getUnifiedSocialCreditCode());
                merchantApply.setPersonName(dto.getPosLegalPerson());
                merchantApply.setPersonIdcardType(dto.getPosLegalPersonType());
                merchantApply.setPersonIdcardNo(dto.getPosLegalPersonIdcard());
                merchantApply.setShopName(dto.getPosName());
                merchantApply.setPosShopId(dto.getPosCode());
                merchantApply.setAccountId(account.getId());
                merchantApply.setShopManName(dto.getAdminName());
                merchantApply.setShopManPhone(dto.getAdminPhone());
                merchantApply.setShopAddress(dto.getPosAddress());
                merchantApply.setShopLatitude(StringUtils.isBlank(dto.getLat())?BigDecimal.ZERO:new BigDecimal(dto.getLat()));
                merchantApply.setShopLongitude(StringUtils.isBlank(dto.getLont())?BigDecimal.ZERO:new BigDecimal(dto.getLont()));
                merchantApply.setShopManEmail(StringUtils.isBlank(dto.getAdminEmail())?"":dto.getAdminEmail());
                merchantApply.setProgress(apply.getProgress());
                if (apply.getState().equals(MerchantApplyStateEnum.通过.getCode())){
                    merchantApply.setState(MerchantApplyStateEnum.待审.getCode());
                }
                // 将base64码解析并上传到oss
                Map<String,String> imgs = getImageBase64(dto.getPosLegalPersonIdcardImgs());
                if (ObjectUtils.isNotEmpty(imgs)){
                    merchantApply.setPersonIdcardBack(StringUtils.isBlank(imgs.get("back"))?"":imgs.get("back"));
                    merchantApply.setPersonIdcardFront(StringUtils.isBlank(imgs.get("front"))?"":imgs.get("front"));
                }
                merchantApplyRepository.saveOrUpdate(merchantApply);
            }else if(ObjectUtils.isNotEmpty(apply.getIsOpen()) && apply.getIsOpen().equals(TrueFalseEnum.是.getCode())){

            }

        }

    }

    public static MultipartFile base64ToMultipart(String base64) {
        try {
            String[] baseStrs = base64.split(",");

            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = new byte[0];
            b = decoder.decodeBuffer(baseStrs[1]);

            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }

            return new BASE64DecodedMultipartFileUtil(b, baseStrs[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Map<String,String> getImageBase64(String posLegalPersonIdcardImgs){
        JSONObject object = JSON.parseObject(posLegalPersonIdcardImgs);
        if (ObjectUtils.isNotEmpty(object)){
            String back = object.getString("back");
            String front = object.getString("front");

            Map<String,String> imgs = new HashMap<>();
            if (StringUtils.isNotBlank(back)){
                PicturesVO.DetailVO  detailVO = fileService.upload(base64ToMultipart(back));
                if (null != detailVO && StringUtils.isNotBlank(detailVO.getImageUrl())){
                    imgs.put("back",detailVO.getImageUrl());
                }
            }
            if (StringUtils.isNotBlank(front)){
                PicturesVO.DetailVO  detailVO2 = fileService.upload(base64ToMultipart(front));
                if (null != detailVO2 && StringUtils.isNotBlank(detailVO2.getImageUrl())){
                    imgs.put("front",detailVO2.getImageUrl());
                }
            }
            return imgs;
        }
        return new HashMap<>();
    }



    private void checkData(PosShopRequestDTO.DTO dto){
        if (null == dto){
            throw new BusinessException("店铺信息参数不能为空！");
        }
        if (ObjectUtils.isEmpty(dto.getTimestamp())){
            throw new BusinessException("当前时间毫秒数不能为空！");
        }
        if (ObjectUtils.isEmpty(dto.getNonce())){
            throw new BusinessException("6位随机字符不能为空！");
        }
        if (ObjectUtils.isEmpty(dto.getPosCode())){
            throw new BusinessException("pos店铺编号不能为空！");
        }
        if (ObjectUtils.isEmpty(dto.getPosAddress())){
            throw new BusinessException("pos店铺地址不能为空！");
        }
        if (ObjectUtils.isEmpty(dto.getLat())){
            throw new BusinessException("店铺纬度不能为空！");
        }
        if (ObjectUtils.isEmpty(dto.getLont())){
            throw new BusinessException("店铺经度不能为空！");
        }
        if (ObjectUtils.isEmpty(dto.getPosLegalPersonType())){
            throw new BusinessException("法人身份证类型不能为空！");
        }
        if (ObjectUtils.isNotEmpty(dto.getPosLegalPersonType()) &&(!dto.getPosLegalPersonType().equals(LegalPersonTypeEnum.中国大陆.getCode()) && dto.getPosLegalPersonType().equals(LegalPersonTypeEnum.非中国大陆.getCode()))){
            throw new BusinessException("法人身份类型错误！");
        }
        if (ObjectUtils.isEmpty(dto.getPosLegalPerson())){
            throw new BusinessException("法人姓名不能为空！");
        }
        if (ObjectUtils.isEmpty(dto.getPosLegalPersonIdcard())){
            throw new BusinessException("法人身份证号或护照号不能为空！");
        }
        if (ObjectUtils.isEmpty(dto.getPosLegalPersonIdcardImgs())){
            throw new BusinessException("法人身份证正反两面不能为空！");
        }
        if (ObjectUtils.isEmpty(dto.getAdminName())){
            throw new BusinessException("店主姓名不能为空！");
        }
        if (ObjectUtils.isEmpty(dto.getAdminPhone())){
            throw new BusinessException("店主手机号不能为空！");
        }
        if (ObjectUtils.isEmpty(dto.getUnifiedSocialCreditCode())){
            throw new BusinessException("统一社会信用代码不能为空！");
        }

    }
}
