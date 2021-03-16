package com.gs.lshly.biz.support.merchant.service.common.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.merchant.entity.MerchantApply;
import com.gs.lshly.biz.support.merchant.entity.MerchantApplyCategory;
import com.gs.lshly.biz.support.merchant.entity.MerchantApplyCert;
import com.gs.lshly.biz.support.merchant.entity.ShopGoodsCategory;
import com.gs.lshly.biz.support.merchant.enums.MerchantApplyCategoryTypeEnum;
import com.gs.lshly.biz.support.merchant.enums.MerchantApplyStateEnum;
import com.gs.lshly.biz.support.merchant.repository.IMerchantApplyCategoryRepository;
import com.gs.lshly.biz.support.merchant.repository.IMerchantApplyCertRepository;
import com.gs.lshly.biz.support.merchant.repository.IMerchantApplyRepository;
import com.gs.lshly.biz.support.merchant.repository.IShopGoodsCategoryRepository;
import com.gs.lshly.biz.support.merchant.service.common.ICommonMerchantApplyService;
import com.gs.lshly.common.enums.*;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.common.CommonShopDTO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.common.LegalDictDTO;
import com.gs.lshly.common.struct.common.LegalDictVO;
import com.gs.lshly.common.struct.common.dto.CommonMerchantApplyDTO;
import com.gs.lshly.common.struct.common.qto.CommonMerchantApplyQTO;
import com.gs.lshly.common.struct.common.vo.CommonMerchantApplyVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsCategoryVO;
import com.gs.lshly.common.utils.EnumUtil;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.middleware.sms.ISMSService;
import com.gs.lshly.rpc.api.common.ICommonSiteCustomerServiceRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsBrandRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsCategoryRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
public class CommonMerchantApplyServiceImpl implements ICommonMerchantApplyService {

    @Autowired
    private IMerchantApplyRepository repository;

    @Autowired
    private IMerchantApplyCertRepository merchantApplyCertRepository;

    @Autowired
    private IMerchantApplyCategoryRepository merchantApplyCategoryRepository;

    @Autowired
    private IShopGoodsCategoryRepository shopGoodsCategoryRepository;

    @DubboReference
    private IPCMerchAdminGoodsBrandRpc ipcMerchAdminGoodsBrandRpc;

    @DubboReference
    private IPCMerchAdminGoodsCategoryRpc pCMerchAdminGoodsCategoryRpc;

    @DubboReference
    private ICommonSiteCustomerServiceRpc commonSiteCustomerServiceRpc;

    @Autowired
    private ISMSService smsService;


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public String editMerchantApply(CommonMerchantApplyDTO.ETO eto) {
        QueryWrapper<MerchantApply> checkQueryWrapper = MybatisPlusUtil.query();
        checkQueryWrapper.eq("account_id", eto.getJwtUserId());
        MerchantApply merchantApply = repository.getOne(checkQueryWrapper);
        if (null == merchantApply) {
            if(!MerchantApplyProgressEnum.企业信息.getCode().equals(eto.getDataType())){
                throw new BusinessException("入驻申请流程顺序错误");
            }
            merchantApply = new MerchantApply();
            merchantApply.setAccountId(eto.getJwtUserId());
            merchantApply.setShopMerchantFrom(MerchantFromTypeEnum.平台入驻.getCode());
            merchantApply.setShopMerchantType(TerminalEnum.BBB.getCode());
            merchantApply.setProgress(eto.getDataType());
            repository.save(merchantApply);
        }
        merchantApply.setState(MerchantApplyStateEnum.待审.getCode());
        if(eto.getDataType() > merchantApply.getProgress() || merchantApply.getProgress().equals(MerchantApplyProgressEnum.进度信息.getCode())){
            merchantApply.setProgress(eto.getDataType());
        }
        if (MerchantApplyProgressEnum.企业信息.getCode().equals(eto.getDataType())) {
            BeanUtils.copyProperties(eto.getCompanyETO(), merchantApply);
            merchantApply.setLegalType(eto.getLegalType());
            repository.updateById(merchantApply);
        } else if (MerchantApplyProgressEnum.银行信息.getCode().equals(eto.getDataType())) {
            BeanUtils.copyProperties(eto.getBankDTO(), merchantApply);
            repository.updateById(merchantApply);
        } else if (MerchantApplyProgressEnum.店铺信息.getCode().equals(eto.getDataType())) {
            if(null == eto.getShopETO()){
                throw new BusinessException("店铺信息不能为空");
            }
            if (ObjectUtils.isEmpty(eto.getShopETO().getCategoryList())) {
                throw new BusinessException("商品分类不能为空");
            }
            if(ShopTypeEnum.品牌旗舰店.getCode().equals(eto.getShopETO().getShopType()) || ShopTypeEnum.品牌专卖店.getCode().equals(eto.getShopETO().getShopType())){
                CommonShopDTO.BrandETO brandETO = eto.getShopETO().getBrand();
                if(null == brandETO){
                    throw new BusinessException("品牌数据不能为空");
                }
                if(!EnumUtil.checkEnumCode(brandETO.getBrandIsNew(),TrueFalseEnum.class)){
                    throw new BusinessException("品牌类型枚举错误[0-1]");
                }
                if(StringUtils.isBlank(brandETO.getBrandName())){
                    throw new BusinessException("品牌名称不能为空");
                }
                if(ObjectUtils.isEmpty(eto.getShopETO().getCategoryList())){
                    throw new BusinessException("商品类目数据错误");
                }
                BeanUtils.copyProperties(brandETO, merchantApply);
            }else{
               ResponseData<Void> responseData = pCMerchAdminGoodsCategoryRpc.innerCheckMerchantApplyCategory(eto.getShopETO().getCategoryList());
               if(responseData.getCode() == ResponseData.BUSINESS_EXCEPTION_CODE){
                   throw new BusinessException(responseData.getMessage());
               }
            }
            BeanUtils.copyProperties(eto.getShopETO(), merchantApply);
            merchantApply.setShopMerchantType(10);
            repository.updateById(merchantApply);
            //删除旧的商品类目
            QueryWrapper<MerchantApplyCategory> removeWrapper = new QueryWrapper<>();
            removeWrapper.eq("apply_id", merchantApply.getId());
            merchantApplyCategoryRepository.remove(removeWrapper);
            List<MerchantApplyCategory> categoryList = new ArrayList<>();
            for (CommonShopDTO.CategoryETO categoryItem : eto.getShopETO().getCategoryList()) {
                MerchantApplyCategory merchantApplyCategory = new MerchantApplyCategory();
                merchantApplyCategory.setApplyId(merchantApply.getId());
                merchantApplyCategory.setGoodsCategoryId(categoryItem.getGoodsCategoryId());
                merchantApplyCategory.setGoodsCategoryName(categoryItem.getGoodsCategoryName());
                merchantApplyCategory.setApplyType(MerchantApplyCategoryTypeEnum.入驻申请.getCode());
                merchantApplyCategory.setState(MerchantApplyStateEnum.待审.getCode());
                categoryList.add(merchantApplyCategory);
            }
            //保存新的商品类目
            merchantApplyCategoryRepository.saveBatch(categoryList);
        } else if (MerchantApplyProgressEnum.证照信息.getCode().equals(eto.getDataType())) {
            if (ObjectUtils.isNotEmpty(eto.getCertListDTO())) {
                List<MerchantApplyCert> certBatchList = new ArrayList<>();
                for (LegalDictDTO.CertDTO certItem : eto.getCertListDTO()) {
                    MerchantApplyCert merchantApplyCert = new MerchantApplyCert();
                    BeanUtils.copyProperties(certItem, merchantApplyCert);
                    merchantApplyCert.setId(null);
                    merchantApplyCert.setCertId(certItem.getId());
                    merchantApplyCert.setApplyId(merchantApply.getId());
                    certBatchList.add(merchantApplyCert);
                }
                //删除旧证照
                QueryWrapper<MerchantApplyCert> removeWrapper = new QueryWrapper<>();
                removeWrapper.eq("apply_id", merchantApply.getId());
                merchantApplyCertRepository.remove(removeWrapper);
                //保存新的
                merchantApplyCertRepository.saveBatch(certBatchList);
                UpdateWrapper<MerchantApply> updateWrapper = MybatisPlusUtil.update();
                updateWrapper.set("progress", MerchantApplyProgressEnum.证照信息.getCode());
                updateWrapper.eq("id",merchantApply.getId());
                repository.update(updateWrapper);
            }
            //获取管理员手机号码
            String phone = commonSiteCustomerServiceRpc.getDataPhone(eto);
            if (StringUtils.isNotBlank(phone)){
                smsService.sendSettlementInformSMSCode(phone,merchantApply.getShopName());
            }
        }

        return merchantApply.getId();
    }

    @Override
    public CommonMerchantApplyVO.DetailVO detailMerchantApply(CommonMerchantApplyDTO.QueryDTO dto) {

        QueryWrapper<MerchantApply> checkQueryWrapper = new QueryWrapper<>();
        checkQueryWrapper.eq("account_id", dto.getJwtUserId());
        MerchantApply merchantApply = repository.getOne(checkQueryWrapper);
        CommonMerchantApplyVO.DetailVO detailVo = new CommonMerchantApplyVO.DetailVO();
        if(null == merchantApply){
            detailVo.setProgress(MerchantApplyProgressEnum.企业信息.getCode());
            return detailVo;
        }else{
            detailVo.setProgress(merchantApply.getProgress());
            if(MerchantApplyProgressEnum.进度信息.getCode().equals(dto.getQueryType())){
                dto.setQueryType(merchantApply.getProgress());
            }
            if(MerchantApplyProgressEnum.等待审核.getCode().equals(dto.getQueryType())){
                detailVo.setId(merchantApply.getId());
            }else if(MerchantApplyProgressEnum.审核失败.getCode().equals(dto.getQueryType())){
                CommonMerchantApplyVO.VerifyVO verifyVO = new CommonMerchantApplyVO.VerifyVO();
                BeanUtils.copyProperties(merchantApply,verifyVO);
                detailVo.setId(merchantApply.getId());
                detailVo.setVerifyVO(verifyVO);
            }else if(MerchantApplyProgressEnum.企业信息.getCode().equals(dto.getQueryType())){
                LegalDictVO.CompanyVO companyVO  = new LegalDictVO.CompanyVO();
                BeanUtils.copyProperties(merchantApply,companyVO);
                detailVo.setId(merchantApply.getId());
                detailVo.setCompanyVO(companyVO);
            }else if(MerchantApplyProgressEnum.银行信息.getCode().equals(dto.getQueryType())){
                LegalDictVO.BankVO bankVO  = new LegalDictVO.BankVO();
                BeanUtils.copyProperties(merchantApply,bankVO);
                detailVo.setId(merchantApply.getId());
                detailVo.setBankVO(bankVO);
            }else if(MerchantApplyProgressEnum.店铺信息.getCode().equals(dto.getQueryType())){
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
                detailVo.setId(merchantApply.getId());
                shopVO.setBrand(brandVO);
                shopVO.setCategoryList(categoryVoList);
                detailVo.setShopVO(shopVO);
            }else if(MerchantApplyProgressEnum.证照信息.getCode().equals(dto.getQueryType())){
                //证照信息
                QueryWrapper<MerchantApplyCert> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("apply_id",merchantApply.getId());
                List<MerchantApplyCert> certList = merchantApplyCertRepository.list(queryWrapper);
                detailVo.setId(merchantApply.getId());
                detailVo.setCertListVO(ListUtil.listCover(LegalDictVO.CertVO.class,certList));
            }else if(MerchantApplyProgressEnum.等待签约.getCode().equals(dto.getQueryType())){
                CommonMerchantApplyVO.WaitSignUpVO waitSignUpVO = new CommonMerchantApplyVO.WaitSignUpVO();
                waitSignUpVO.setCdate(merchantApply.getCdate());
                waitSignUpVO.setOkpassTime(merchantApply.getOkpassTime());
            }
            return detailVo;
        }
    }

    @Override
    public void editApplyCategory(CommonMerchantApplyDTO.ApplyCategoryDTO dto) {
        if(null == dto.getCategory() || StringUtils.isBlank(dto.getCategory().getGoodsCategoryId())
                || StringUtils.isBlank(dto.getCategory().getGoodsCategoryName())){
            throw new BusinessException("申请的商品类目信息错误");
        }
        String applyCategoryId = dto.getCategory().getGoodsCategoryId();
        String applyCategoryName = dto.getCategory().getGoodsCategoryName();
        QueryWrapper<ShopGoodsCategory> shopGoodsCategoryQueryWrapper = MybatisPlusUtil.query();
        shopGoodsCategoryQueryWrapper.eq("terminal",dto.getTerminal());
        shopGoodsCategoryQueryWrapper.eq("category_id",applyCategoryId);
        shopGoodsCategoryQueryWrapper.eq("shop_id",dto.getJwtShopId());
        List<ShopGoodsCategory> shopGoodsCategoryList = shopGoodsCategoryRepository.list(shopGoodsCategoryQueryWrapper);
        if(ObjectUtils.isNotEmpty(shopGoodsCategoryList)){
            throw new BusinessException("申请的商品类目店铺以存在");
        }
        QueryWrapper<MerchantApplyCategory> merchantApplyCategoryQueryWrapper = MybatisPlusUtil.query();
        merchantApplyCategoryQueryWrapper.eq("terminal",dto.getTerminal());
        merchantApplyCategoryQueryWrapper.eq("goods_category_id",applyCategoryId);
        merchantApplyCategoryQueryWrapper.eq("shop_id",dto.getJwtShopId());
        List<MerchantApplyCategory> applyCategoryList = merchantApplyCategoryRepository.list(merchantApplyCategoryQueryWrapper);
        if(ObjectUtils.isNotEmpty(applyCategoryList)){
            throw new BusinessException("提交申请的商品类目以在申请列表中");
        }
        MerchantApplyCategory merchantApplyCategory = new MerchantApplyCategory();
        merchantApplyCategory.setTerminal(dto.getTerminal());
        merchantApplyCategory.setApplyType(MerchantApplyCategoryTypeEnum.店铺申请.getCode());
        merchantApplyCategory.setState(MerchantApplyStateEnum.待审.getCode());
        merchantApplyCategory.setApplyWhy(dto.getApplyWhy());
        merchantApplyCategory.setGoodsCategoryId(applyCategoryId);
        merchantApplyCategory.setGoodsCategoryName(applyCategoryName);
        merchantApplyCategory.setShopId(dto.getJwtShopId());
        merchantApplyCategory.setMerchantId(dto.getJwtMerchantId());
        merchantApplyCategoryRepository.save(merchantApplyCategory);
    }

    @Override
    public List<PCMerchGoodsCategoryVO.ListVO> categoryList(CommonMerchantApplyQTO.CategoryQTO dto) {
        List<PCMerchGoodsCategoryVO.ListVO> goodsCategoryList =  pCMerchAdminGoodsCategoryRpc.level1Categories();
        QueryWrapper<ShopGoodsCategory> shopGoodsCategoryQueryWrapper = MybatisPlusUtil.query();
        //店铺以有的一级商品分类
        shopGoodsCategoryQueryWrapper.eq("terminal",dto.getTerminal());
        shopGoodsCategoryQueryWrapper.eq("category_leve",GoodsCategoryLevelEnum.ONE.getCode());
        shopGoodsCategoryQueryWrapper.eq("shop_id",dto.getJwtShopId());
        List<ShopGoodsCategory> shopGoodsCategoryList = shopGoodsCategoryRepository.list(shopGoodsCategoryQueryWrapper);
        if(ObjectUtils.isNotEmpty(shopGoodsCategoryList)){
            for(ShopGoodsCategory shopGoodsCategory:shopGoodsCategoryList){
               for(PCMerchGoodsCategoryVO.ListVO listVO:goodsCategoryList){
                   if(listVO.getId().equals(shopGoodsCategory.getCategoryId())){
                       goodsCategoryList.remove(listVO);
                       break;
                   }
               }
            }
        }
        return goodsCategoryList;
    }

    @Override
    public PageData<CommonMerchantApplyVO.ApplyCategoryRecordVO> applyCategoryRecord(CommonMerchantApplyQTO.ApplyCategoryQTO qto) {
        QueryWrapper<MerchantApplyCategory> wrapper =MybatisPlusUtil.query();
        wrapper.eq("terminal",qto.getTerminal());
        wrapper.eq("apply_type",MerchantApplyCategoryTypeEnum.店铺申请.getCode());
        wrapper.eq("shop_id",qto.getJwtShopId());
        IPage<MerchantApplyCategory> page = MybatisPlusUtil.pager(qto);
        merchantApplyCategoryRepository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, CommonMerchantApplyVO.ApplyCategoryRecordVO.class, page);
    }

    @Override
    public void deleteCategoryRecord(CommonMerchantApplyDTO.IdDTO dto) {
        QueryWrapper<MerchantApplyCategory> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("shop_id",dto.getJwtShopId());
        queryWrapper.eq("id",dto.getId());
        MerchantApplyCategory merchantApplyCategory = merchantApplyCategoryRepository.getOne(queryWrapper);
        if(null == merchantApplyCategory){
            throw new BusinessException("商品类目申请不存在");
        }
        if(!MerchantApplyStateEnum.待审.getCode().equals(merchantApplyCategory)){
            throw new BusinessException("只能删除待审状态的申请");
        }
        merchantApplyCategoryRepository.removeById(merchantApplyCategory.getId());
    }

}
