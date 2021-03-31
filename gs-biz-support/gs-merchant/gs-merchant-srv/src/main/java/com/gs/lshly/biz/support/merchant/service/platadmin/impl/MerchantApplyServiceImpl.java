package com.gs.lshly.biz.support.merchant.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.merchant.entity.*;
import com.gs.lshly.biz.support.merchant.enums.MerchantApplyQueryTypeEnum;
import com.gs.lshly.biz.support.merchant.enums.MerchantApplyStateEnum;
import com.gs.lshly.biz.support.merchant.enums.UdateSerchTypeEnum;
import com.gs.lshly.biz.support.merchant.mapper.MerchantApplyMapper;
import com.gs.lshly.biz.support.merchant.mapper.views.MerchantApplyView;
import com.gs.lshly.biz.support.merchant.repository.*;
import com.gs.lshly.biz.support.merchant.service.platadmin.IMerchantApplyService;
import com.gs.lshly.common.enums.*;
import com.gs.lshly.common.enums.merchant.ShopStateEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.common.LegalDictDTO;
import com.gs.lshly.common.struct.common.LegalDictVO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsBrandDTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsCategoryVO;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantApplyDTO;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopTypeDictDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantApplyQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantApplyVO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopTypeDictVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeMarginDTO;
import com.gs.lshly.common.struct.platadmin.user.dto.UserDTO;
import com.gs.lshly.common.struct.platadmin.user.vo.UserVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.DateUtils;
import com.gs.lshly.common.utils.EnumUtil;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.common.utils.PwdUtil;
import com.gs.lshly.common.utils.SettleNoUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.middleware.sms.ISMSService;
import com.gs.lshly.rpc.api.common.ILegalDictRpc;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsBrandRpc;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsCategoryRpc;
import com.gs.lshly.rpc.api.platadmin.merchant.IShopTypeDictRpc;
import com.gs.lshly.rpc.api.platadmin.trade.ITradeMarginRpc;
import com.gs.lshly.rpc.api.platadmin.user.IUserRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-14
*/
@Component
public class MerchantApplyServiceImpl implements IMerchantApplyService {

    @Autowired
    private IMerchantApplyRepository repository;
    @Autowired
    private MerchantApplyMapper merchantApplyMapper;
    @Autowired
    private IMerchantRepository merchantRepository;
    @Autowired
    private IMerchantAccountRepository merchantAccountRepository;
    @Autowired
    private IMerchantApplyCertRepository merchantApplyCertRepository;
    @Autowired
    private IShopRepository shopRepository;
    @Autowired
    private IShopGoodsCategoryRepository shopGoodsCategoryRepository;
    @Autowired
    private IMerchantApplyCategoryRepository merchantApplyCategoryRepository;
    @DubboReference
    private ILegalDictRpc legalDictRpc;
    @DubboReference
    private IGoodsBrandRpc goodsBrandRpc;
    @DubboReference
    private IGoodsCategoryRpc goodsCategoryRpc;
    @DubboReference
    private ITradeMarginRpc tradeMarginRpc;
    @DubboReference
    private IShopTypeDictRpc shopTypeDictRpc;
    @DubboReference
    private IUserRpc userRpc;

    @Autowired
    private ISMSService smsService;


    @Override
    public PageData<MerchantApplyVO.ListVO> pageData(MerchantApplyQTO.QTO qto) {
        if(!EnumUtil.checkEnumCode(qto.getState(),MerchantApplyStateEnum.class)){
            throw new BusinessException("核审状态枚举类型错误");
        }
        QueryWrapper<MerchantApplyView> wrapper =  MybatisPlusUtil.query();
        if(!MerchantApplyStateEnum.全部.getCode().equals(qto.getState())){
            wrapper.eq("apply.state",qto.getState());
        }
        if(StringUtils.isNotBlank(qto.getConditionValue())){
            if( MerchantApplyQueryTypeEnum.姓名.getCode().equals(qto.getConditionType())){
                wrapper.like("apply.shop_man_name",qto.getConditionValue());
            }else if( MerchantApplyQueryTypeEnum.手机号.getCode().equals(qto.getConditionType())){
                wrapper.like("apply.shop_man_phone",qto.getConditionValue());
            }
        }
        IPage<MerchantApplyView> page = MybatisPlusUtil.pager(qto);
        merchantApplyMapper.mapperPageList(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, MerchantApplyVO.ListVO.class, page);
    }

    @Override
    public PageData<MerchantApplyVO.ListVO> pageSearch(MerchantApplyQTO.SearchQTO qto) {
        QueryWrapper<MerchantApplyView> queryWrapper =  MybatisPlusUtil.query();

        queryWrapper.and(wrapper ->{
            if(null != qto.getState()){
                if(!MerchantApplyStateEnum.全部.getCode().equals(qto.getState())){
                    if(MerchantApplyStateEnum.已开店.getCode().equals(qto.getState())){
                        wrapper.or().eq("apply.is_open",TrueFalseEnum.是.getCode());
                    }else{
                        wrapper.or().eq("apply.state",qto.getState());
                    }
                }
            }
            if(null != qto.getShopType()){
                wrapper.or().eq("apply.shop_type",qto.getShopType());
            }

            if(null != qto.getUdateType()){
                if(UdateSerchTypeEnum.今日.getCode().equals(qto.getUdateType())){
                    wrapper.or().apply("DATE_FORMAT(apply.udate,'%Y-%m-%d') = DATE_FORMAT('" + LocalDateTime.now() +"','%Y-%m-%d')");
                }else if(UdateSerchTypeEnum.昨日.getCode().equals(qto.getUdateType())){
                    wrapper.or().apply("DATE_FORMAT(apply.udate,'%Y-%m-%d') = DATE_FORMAT('" + LocalDateTime.now().minusDays(1) + "','%Y-%m-%d')");
                }else if(UdateSerchTypeEnum.最近7天.getCode().equals(qto.getUdateType())){
                    wrapper.or().apply("DATE_FORMAT(apply.udate,'%Y-%m-%d') = DATE_FORMAT('" + LocalDateTime.now().minusDays(7) + "','%Y-%m-%d')");
                }else if(UdateSerchTypeEnum.最近30天.getCode().equals(qto.getUdateType())){
                    wrapper.or().apply("DATE_FORMAT(apply.udate,'%Y-%m-%d') = DATE_FORMAT('" + LocalDateTime.now().minusDays(30) + "','%Y-%m-%d')");
                }
            }
            if(StringUtils.isNotBlank(qto.getUserName())){
                wrapper.or().like("acc.user_name",qto.getUserName());
            }
            if(StringUtils.isNotBlank(qto.getShopName())){
                wrapper.or().like("apply.shop_name",qto.getShopName());
            }
            if(StringUtils.isNotBlank(qto.getShopManName())){
                wrapper.or().like("apply.shop_man_name",qto.getShopManName());
            }
            if(StringUtils.isNotBlank(qto.getCorpName())){
                wrapper.or().like("apply.corp_name",qto.getCorpName());
            }
        });
        IPage<MerchantApplyView> page = MybatisPlusUtil.pager(qto);
        merchantApplyMapper.mapperPageList(page, queryWrapper);
        return MybatisPlusUtil.toPageData(qto, MerchantApplyVO.ListVO.class, page);
    }

    @Override
    public void deleteBatchMerchantApply(MerchantApplyDTO.IdListDTO dto) {
        if(ObjectUtils.isNull(dto.getIdList())){
            throw new BusinessException("ID数组不能为Null");
        }
        if(!repository.checkIdListExist(dto.getIdList())){
            throw new BusinessException("无效的ID");
        }
        repository.removeByIds(dto.getIdList());
    }

    @Override
    public void apply(MerchantApplyDTO.ApplyDTO dto) {

        if(StringUtils.isBlank(dto.getId())){
           throw new BusinessException("ID不能为空");
        }
        if(!EnumUtil.checkEnumCodeWithExcludes(dto.getState(),MerchantApplyStateEnum.class,MerchantApplyStateEnum.全部.getCode())){
            throw new BusinessException("审核枚举不存在");
        }
        MerchantApply merchantApply =  repository.getById(dto.getId());
        if(null == merchantApply){
            throw new BusinessException("入驻申请不在存");
        }
        if(dto.getState().equals(MerchantApplyStateEnum.拒审.getCode())){
            merchantApply.setState(MerchantApplyStateEnum.拒审.getCode());
            merchantApply.setProgress(MerchantApplyProgressEnum.审核失败.getCode());
            merchantApply.setRejectTime(LocalDateTime.now());
            merchantApply.setRejectWhy(dto.getRejectWhy());
            repository.updateById(merchantApply);
        }else if(dto.getState().equals(MerchantApplyStateEnum.通过.getCode())){
            //审核需要检查新增的品牌是否以经在品牌库中已创建
            if(TrueFalseEnum.是.getCode().equals(merchantApply.getBrandIsNew())){
               throw new BusinessException("先在品牌库中创建【"+merchantApply.getBrandName()+"】");
            }
            merchantApply.setLakalaNo(dto.getLakalaNo());
            merchantApply.setState(MerchantApplyStateEnum.通过.getCode());
            merchantApply.setProgress(MerchantApplyProgressEnum.等待签约.getCode());
            merchantApply.setOkpassTime(LocalDateTime.now());
            
            merchantApply.setExpirationTime(DateUtils.parseDate(DateUtils.DATE_YYYY_MM_DD_PATTERN, dto.getExpirationTime()));
            merchantApply.setAgreementCode(dto.getAgreementCode());
            merchantApply.setTaxType(dto.getTaxType());
            merchantApply.setTaxRate(dto.getTaxRate());
            repository.updateById(merchantApply);
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void openShop(MerchantApplyDTO.IdDTO dto) {
        if(StringUtils.isBlank(dto.getId())){
            throw new BusinessException("入驻申请ID不能为空");
        }
        MerchantApply merchantApply =  repository.getById(dto.getId());
        if(null == merchantApply){
            throw new BusinessException("入驻申请不在存");
        }
        if(!MerchantApplyStateEnum.通过.getCode().equals(merchantApply.getState())){
            throw new BusinessException("入驻申请审核通过后，才能开通店铺");
        }
        if(TrueFalseEnum.是.getCode().equals(merchantApply.getIsOpen())){
            throw new BusinessException("店铺已经开通");
        }
        LegalDictVO.DetailVO legalDictVo = this.findOrCreateLegalDict(merchantApply,BusinessTypeEnum.卖家.getCode());
        //查找企业字典关联的商家,如果没有商家则创建一个商家，商家的名称就是企业公司名称
        QueryWrapper<Merchant> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("legal_id",legalDictVo.getId());
        Merchant merchant = merchantRepository.getOne(queryWrapper);
        if(null == merchant){
            merchant = new Merchant().setLegalId(legalDictVo.getId()).setIsPlatform(TrueFalseEnum.否.getCode());
            if(null != legalDictVo.getCompanyVO()){
                merchant.setMerchantName(legalDictVo.getCompanyVO().getCorpName());
            }
            merchant.setLakalaNo(merchantApply.getLakalaNo());
            merchant.setAddress(merchantApply.getShopAddress());
            merchant.setAgreementCode(merchantApply.getAgreementCode());
            String provinceCity = merchantApply.getCorpCityAddress();
            if(org.apache.commons.lang3.StringUtils.isNotEmpty(provinceCity)){
            	String args[] = provinceCity.split(",");
            	if(args.length>1){
            		merchant.setCity(args[1]);
            		merchant.setProvince(args[0]);
            	}
            }
            merchant.setEmail(merchantApply.getShopManEmail());
            merchant.setExpirationTime(merchantApply.getExpirationTime());
            merchant.setLinkMan(merchantApply.getShopManName());
            merchant.setLinkPhone(merchantApply.getShopManPhone());
            merchant.setTaxRate(merchantApply.getTaxRate());
            merchant.setTaxType(merchantApply.getTaxType());
            merchant.setType(merchantApply.getType());
            merchantRepository.save(merchant);
        }
        //创建店铺信息,并把店铺的主帐号设置为申请入驻时登录的帐号
        Shop shop  = new Shop();
        BeanUtils.copyProperties(merchantApply,shop);
        shop.setId(null);
        shop.setShareCode(SettleNoUtil.getShopShareCode());
        shop.setShopType(merchantApply.getShopType());
        shop.setOpenTime(LocalDateTime.now());
        shop.setShopState(ShopStateEnum.开启状态.getCode());
        shop.setTerminal(merchantApply.getShopMerchantType());
        shop.setMainAccountId(merchantApply.getAccountId());
        shop.setMerchantId(merchant.getId());
        shop.setPosShopId(ObjectUtils.isEmpty(merchantApply.getPosShopId())?"":merchantApply.getPosShopId());
        shopRepository.save(shop);
        //为商家帐号关联店铺ID
        MerchantAccount merchantAccount = new MerchantAccount();
        merchantAccount.setId(merchantApply.getAccountId());
        merchantAccount.setShopId(shop.getId());
        merchantAccount.setMerchantId(shop.getMerchantId());
        merchantAccountRepository.updateById(merchantAccount);
        //查找申请的一级商品类目
        QueryWrapper<MerchantApplyCategory> merchantApplyCategoryQueryWrapper = MybatisPlusUtil.query();
        merchantApplyCategoryQueryWrapper.eq("apply_id",merchantApply.getId());
        List<MerchantApplyCategory> merchantApplyCategoryList = merchantApplyCategoryRepository.list(merchantApplyCategoryQueryWrapper);
        if(ObjectUtils.isEmpty(merchantApplyCategoryList)){
            throw new BusinessException("商家提供的入驻申请数据没有商品分类");
        }
        //把商品分类1-3级复制到店铺申请的分类下,供平台设置三级商品类目的分成比率（自营店是全类目也不需要编辑费用，没有复制）
        List<String> categoryIdList = ListUtil.getIdList(MerchantApplyCategory.class,merchantApplyCategoryList,"goodsCategoryId");
        List<GoodsCategoryVO.CategoryTreeVO> categoryTree = goodsCategoryRpc.listCategoryTree(categoryIdList);
        if (ObjectUtils.isEmpty(categoryTree)) {
            throw new BusinessException("商品类目数据错误");
        }
        if (ObjectUtils.isNotEmpty(categoryTree)) {
            shopGoodsCategoryRepository.shopGoodsCategoryBatchSave(shop.getMerchantId(),shop.getId(),categoryTree);
        }
        //根据店铺的类型 查询默认的保证金信息 为交易中心生成默认保证金数据
        tradeMarginRpc.InnerCreateShopMargin(this.createInnerCreateMargin(shop));
        //入驻申请的状态改成以已开通店铺
        merchantApply.setShopId(shop.getId());
        merchantApply.setMerchantId(shop.getMerchantId());
        merchantApply.setIsOpen(TrueFalseEnum.是.getCode());
        repository.updateById(merchantApply);

        createDefaultUserAccount(legalDictVo.getId(),merchantAccount.getId());
    }
    private void createDefaultUserAccount(String legalDictId,String accountId){
        MerchantAccount account = merchantAccountRepository.getById(accountId);
        /**
         * 商家入驻成功后，若没有会员账号则生成一个默认的企业会员账号
         * 修改改企业商业类型
         * 1.查看是否该手机号已经被在商城注册
         * 2.若已注册，会员的角色是2c,则直接升级为2b,若角色为2b则不生成
         * 3.若没有注册直接生成企业会员账号
         */
        UserVO.ListVO listVO = userRpc.innerByPhone(account.getPhone());
        if (ObjectUtils.isNotEmpty(listVO) && listVO.getType().equals(UserTypeEnum._2C用户.getCode())){
            legalDictRpc.innerUpdateLegalDict(legalDictId,BusinessTypeEnum.全部.getCode());
            UserDTO.InnerETO eto = new UserDTO.InnerETO();
            eto.setType(UserTypeEnum._2B用户.getCode());
            eto.setLegalId(legalDictId);
            eto.setId(listVO.getId());
            eto.setState(UserStateEnum.启用.getCode());
            UserVO.ListVO user = userRpc.innerSave2BUser(eto);

        }
        if (ObjectUtils.isEmpty(listVO)){
            legalDictRpc.innerUpdateLegalDict(legalDictId,BusinessTypeEnum.全部.getCode());
            UserDTO.InnerETO eto = new UserDTO.InnerETO();
            eto.setLegalId(legalDictId);
            eto.setPhone(account.getPhone());
            eto.setUserName(account.getPhone());
            eto.setState(UserStateEnum.启用.getCode());
            eto.setType(UserTypeEnum._2B用户.getCode());
            eto.setUserPwd(PwdUtil.encode("123456"));
            UserVO.ListVO user = userRpc.innerSave2BUser(eto);
            smsService.sendSettlementSMSCode(account.getPhone(),account.getPhone(),"123456");
        }

    }

    private TradeMarginDTO.InnerCreateMargin createInnerCreateMargin(Shop shop){
        if(null == shop || StringUtils.isBlank(shop.getId())){
            throw new BusinessException("店铺ID不能为空");
        }
        ShopTypeDictVO.DetailVO detailVO =  shopTypeDictRpc.detailsShopTypeDict(new ShopTypeDictDTO.ShopTypeDTO(shop.getShopType()));
        if(null == detailVO){
            throw new BusinessException("店铺类型不存在");
        }
        TradeMarginDTO.InnerCreateMargin createMargin = new TradeMarginDTO.InnerCreateMargin();
        createMargin.setShopId(shop.getId());
        createMargin.setShopName(shop.getShopName());
        createMargin.setShopType(shop.getShopType());
        createMargin.setMerchantId(shop.getMerchantId());
        createMargin.setMarginQuota(detailVO.getBail());
        createMargin.setMarginDown(detailVO.getBailDown());
        createMargin.setMarginType(TradeMarginEnum.正常.getCode());
        return createMargin;
    }


    private LegalDictVO.DetailVO findOrCreateLegalDict(MerchantApply merchantApply,Integer businessType){
        //用营业执照号 查询企业字典
        LegalDictDTO.LicenseNumDTO licenseNumDTO = new LegalDictDTO.LicenseNumDTO();
        licenseNumDTO.setCorpLicenseNum(merchantApply.getCorpLicenseNum());
        LegalDictVO.DetailVO  legalDictVo = legalDictRpc.detailLegalDict(licenseNumDTO);
        //企业字典不存在，需要先检查相关证照是否以上传,再创建企业典
        if(null == legalDictVo){
            QueryWrapper<MerchantApplyCert> merchantApplyCertWrapper = MybatisPlusUtil.query();
            merchantApplyCertWrapper.eq("apply_id",merchantApply.getId());
            List<MerchantApplyCert> merchantApplyCertList = merchantApplyCertRepository.list(merchantApplyCertWrapper);
            List<String> idList = ListUtil.getIdList(MerchantApplyCert.class,merchantApplyCertList,"certId");
            LegalDictVO.CheckCertVO checkCertVo = legalDictRpc.checkUploadCert(merchantApply.getCorpTypeId(),idList);
            if(checkCertVo.getCertList().size() > 0){
                throw new BusinessException("入驻申请企业类型必传证照未传");
            }
            //创建企业典数据
            LegalDictDTO.ETO  legalDictDTO = new LegalDictDTO.ETO();
            legalDictDTO.setCertList(new ArrayList<>());
            BeanUtils.copyProperties(merchantApply,legalDictDTO);
            legalDictDTO.setId("");
            legalDictDTO.setBusinessType(businessType);
            legalDictDTO.setLegalType(LegalTypeEnum.企业.getCode());
            for(MerchantApplyCert applyCert:merchantApplyCertList){
                LegalDictDTO.CertDTO certDTO  = new LegalDictDTO.CertDTO();
                BeanUtils.copyProperties(applyCert,certDTO);
                legalDictDTO.getCertList().add(certDTO);
            }
            //创建企业典,并返回数据ID
            String legalId = legalDictRpc.addLegalDict(legalDictDTO);
            //生成数据VO对象
            legalDictVo = new LegalDictVO.DetailVO();
            LegalDictVO.CompanyVO companyVO = new LegalDictVO.CompanyVO();
            BeanUtils.copyProperties(merchantApply,companyVO);
            legalDictVo.setCompanyVO(companyVO);
            legalDictVo.setId(legalId);
        }
        return legalDictVo;
    }

    @Override
    public MerchantApplyVO.BrandVO handBrandQuery(MerchantApplyDTO.IdDTO dto) {
        if(StringUtils.isBlank(dto.getId())){
            throw new BusinessException("入驻申请ID不能为空");
        }
        MerchantApply merchantApply =  repository.getById(dto.getId());
        if(null == merchantApply){
            throw new BusinessException("入驻申请不在存");
        }
        if(ShopTypeEnum.品牌专卖店.getCode().equals(merchantApply.getShopType()) || ShopTypeEnum.品牌旗舰店.getCode().equals(merchantApply.getShopType())){
            if(!TrueFalseEnum.是.getCode().equals(merchantApply.getBrandIsNew())){
                throw new BusinessException("不是新增品牌,不需要处理");
            }
            MerchantApplyVO.BrandVO brandVO = new MerchantApplyVO.BrandVO();
            BeanUtils.copyProperties(merchantApply,brandVO);
            QueryWrapper<MerchantApplyCategory> categoryQueryWrapper = MybatisPlusUtil.query();
            categoryQueryWrapper.eq("apply_id",merchantApply.getId());
            List<MerchantApplyCategory> categoryList =  merchantApplyCategoryRepository.list(categoryQueryWrapper);
            if(ObjectUtils.isNotEmpty(categoryList)){
                MerchantApplyCategory category = categoryList.get(0);
                brandVO.setGoodsCategoryId(category.getGoodsCategoryId());
                brandVO.setGoodsCategoryName(category.getGoodsCategoryName());
            }
            return brandVO;
        }else{
            throw new BusinessException("不是品牌专卖店和品牌旗舰店");
        }
    }

    @Override
    public void handBrandSubmit(MerchantApplyDTO.HandBrandDTO dto) {
        if(StringUtils.isBlank(dto.getId())){
            throw new BusinessException("入驻申请ID不能为空");
        }
        MerchantApply merchantApply =  repository.getById(dto.getId());
        if(null == merchantApply){
            throw new BusinessException("入驻申请不在存");
        }
        if(TrueFalseEnum.是.getCode().equals(merchantApply.getBrandIsNew())){
            GoodsBrandDTO.ETO brandDto = new GoodsBrandDTO.ETO();
            brandDto.setBrandName(merchantApply.getBrandName());
            brandDto.setBrandAlias(dto.getBrandAlias());
            brandDto.setBrandLogo(dto.getBrandLogo());
            if(null == dto.getIdx()){
                dto.setIdx(0);
            }
            brandDto.setIdx(dto.getIdx());
            String brandId = goodsBrandRpc.innerAddGoodsBrand(brandDto);
            MerchantApply updateMerchantApply = new MerchantApply();
            updateMerchantApply.setId(merchantApply.getId());
            updateMerchantApply.setBrandId(brandId);
            updateMerchantApply.setBrandIsNew(TrueFalseEnum.否.getCode());
            repository.updateById(updateMerchantApply);
        }
    }
    @Override
    public MerchantApplyVO.DetailVO detailMerchantApply(MerchantApplyDTO.IdDTO dto) {

        MerchantApply merchantApply = repository.getById(dto.getId());
        if(null == merchantApply){
            throw new BusinessException("入驻申请信息不存在");
        }
        MerchantApplyVO.DetailVO detailVo = new MerchantApplyVO.DetailVO();
        detailVo.setId(merchantApply.getId());
        detailVo.setLakalaNo(merchantApply.getLakalaNo());
        LegalDictVO.CompanyVO companyVO  = new LegalDictVO.CompanyVO();
        BeanUtils.copyProperties(merchantApply,companyVO);
        detailVo.setCompanyVO(companyVO);

        LegalDictVO.BankVO bankVO  = new LegalDictVO.BankVO();
        BeanUtils.copyProperties(merchantApply,bankVO);
        detailVo.setId(merchantApply.getId());
        detailVo.setBankVO(bankVO);

        //店铺
        CommonShopVO.ListVO shopVO = new CommonShopVO.ListVO();
        BeanUtils.copyProperties(merchantApply,shopVO);
        //品牌
        CommonShopVO.BrandVO brandVO = new CommonShopVO.BrandVO();
        BeanUtils.copyProperties(merchantApply,brandVO);
        //商品类目信息
        QueryWrapper<MerchantApplyCategory> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("apply_id",merchantApply.getId());
        List<MerchantApplyCategory> categoryList =  merchantApplyCategoryRepository.list(categoryQueryWrapper);
        List<CommonShopVO.CategoryVO> categoryVoList = ListUtil.listCover(CommonShopVO.CategoryVO.class,categoryList);
        shopVO.setBrand(brandVO);
        shopVO.setCategoryList(categoryVoList);
        detailVo.setShopVO(shopVO);

        //证照信息
        QueryWrapper<MerchantApplyCert> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("apply_id",merchantApply.getId());
        List<MerchantApplyCert> certList = merchantApplyCertRepository.list(queryWrapper);
        detailVo.setId(merchantApply.getId());
        detailVo.setCertListVO(ListUtil.listCover(LegalDictVO.CertVO.class,certList));

        return detailVo;
    }

}
