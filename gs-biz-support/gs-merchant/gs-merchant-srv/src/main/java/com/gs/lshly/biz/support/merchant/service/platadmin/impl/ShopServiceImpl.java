package com.gs.lshly.biz.support.merchant.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.merchant.entity.*;
import com.gs.lshly.biz.support.merchant.mapper.MerchantMapper;
import com.gs.lshly.biz.support.merchant.mapper.ShopGoodsCategoryMapper;
import com.gs.lshly.biz.support.merchant.mapper.ShopMapper;
import com.gs.lshly.biz.support.merchant.mapper.views.MerchantComplexView;
import com.gs.lshly.biz.support.merchant.mapper.views.MerchantShopView;
import com.gs.lshly.biz.support.merchant.repository.*;
import com.gs.lshly.common.enums.*;
import com.gs.lshly.biz.support.merchant.enums.ShopSearchStyleEnum;
import com.gs.lshly.common.enums.merchant.ShopStateEnum;
import com.gs.lshly.biz.support.merchant.service.platadmin.IShopService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.LegalDictDTO;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.ShopQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.common.utils.SettleNoUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.common.ILegalDictRpc;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsInfoRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-13
*/
@Component
public class ShopServiceImpl implements IShopService {

    @Autowired
    private IShopRepository repository;

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private MerchantMapper merchantMapper;



    @Autowired
    private IMerchantRepository merchantRepository;

    @Autowired
    private IMerchantAccountRepository merchantAccountRepository;


    @Autowired
    private ShopGoodsCategoryMapper shopGoodsCategoryMapper;


    @Autowired
    private IShopGoodsCategoryRepository shopGoodsCategoryRepository;

    @DubboReference
    private ILegalDictRpc legalDictRpc;

    @DubboReference
    private IGoodsInfoRpc goodsInfoRpc;

    @Autowired
    private IMerchantApplyCategoryRepository merchantShopCategoryApplyRepository;


    @Override
    public PageData<ShopVO.ListVO> pageListShop(ShopQTO.QTO qto) {
        QueryWrapper<MerchantShopView> wrapper = MybatisPlusUtil.query();
        if(!ObjectUtils.isNull(qto.getConditionType(),qto.getConditionValue())){
            if(qto.getConditionType().equals(ShopSearchStyleEnum.店铺名称.getCode())){
                wrapper.eq("shop.shop_name",qto.getConditionValue());
            }
        }
        wrapper.eq("shop.terminal", qto.getTerminal());
        IPage<MerchantShopView> page = MybatisPlusUtil.pager(qto);
        shopMapper.listShopComplex(page,wrapper);
        return MybatisPlusUtil.toPageData(qto, ShopVO.ListVO.class, page);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void addSelftShop(ShopDTO.ETO dto) {
        QueryWrapper<Merchant> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("is_platform",TrueFalseEnum.是.getCode());
        Merchant merchant = merchantRepository.getOne(queryWrapper);
        if(null  == merchant){
            Merchant merchant1 = new Merchant();
            merchant1.setMerchantName("平台自营商家").setTerminal(10).setLegalId("1322844241059500034").setIsPlatform(1);
            merchantRepository.save(merchant1);
        }
        Shop shop = new Shop();
        BeanCopyUtils.copyProperties(dto, shop);
        shop.setShopState(ShopStateEnum.开启状态.getCode());
        shop.setShopType(ShopTypeEnum.运营商自营.getCode());
        shop.setShopTypeName(ShopTypeEnum.运营商自营.getRemark());
        shop.setTerminal(dto.getTerminal());
        shop.setShareCode(SettleNoUtil.getShopShareCode());
        shop.setShopMerchantFrom(MerchantFromTypeEnum.平台添加.getCode());
        shop.setMerchantId(merchant.getId());
        repository.save(shop);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void editorSelftShop(ShopDTO.ETO dto) {
        Shop shop = new Shop();
        BeanCopyUtils.copyProperties(dto, shop);
        repository.updateById(shop);
    }

    @Override
    public void closeShop(ShopDTO.IdDTO dto) {
        if(StringUtils.isBlank(dto.getId())){
            throw new BusinessException("店铺ID不能为空");
        }
        Shop shop =  repository.getById(dto.getId());
        if(null == shop){
            throw new BusinessException("店铺不在存在");
        }
        if(!ShopStateEnum.关闭状态.getCode().equals(shop.getShopState())){
            shop.setShopState(ShopStateEnum.关闭状态.getCode());
            shop.setCloseTime(LocalDateTime.now());
            repository.updateById(shop);
            List<String> shopIdList = new ArrayList<>();
            shopIdList.add(dto.getId());
            goodsInfoRpc.innerServiceUnderShelfGoods(shopIdList);
        }
    }

    @Override
    public void openShop(ShopDTO.IdDTO dto) {
        if(StringUtils.isBlank(dto.getId())){
            throw new BusinessException("店铺ID不能为空");
        }
        Shop shop =  repository.getById(dto.getId());
        if(null == shop){
            throw new BusinessException("店铺不在存在");
        }
        shop.setShopState(ShopStateEnum.开启状态.getCode());
        shop.setOpenTime(LocalDateTime.now());
        repository.updateById(shop);
    }

    @Override
    public List<ShopVO.IdNameVO> selfShopList(ShopQTO.SelfShopQTO qto) {
        QueryWrapper<Shop> wrapper = MybatisPlusUtil.query();
        wrapper.eq("terminal",qto.getTerminal());
        wrapper.eq("shop_type", ShopTypeEnum.运营商自营.getCode());
        wrapper.isNull("main_account_id");
        List<Shop> dataList = repository.list(wrapper);
        return ListUtil.listCover( ShopVO.IdNameVO.class,dataList);
    }

    @Override
    public ShopVO.SelfShopDetailVO selfShopDetails(ShopDTO.IdDTO dto) {
        QueryWrapper<MerchantShopView> wrapper = MybatisPlusUtil.query();
        wrapper.eq("shop.id",dto.getId());
        wrapper.eq("shop.shop_type",ShopTypeEnum.运营商自营.getCode());
        MerchantShopView viewItem = shopMapper.getShopComplex(wrapper);
        if(null == viewItem){
            return null;
        }
        ShopVO.SelfShopDetailVO selfShopDetailVO = new ShopVO.SelfShopDetailVO();
        BeanCopyUtils.copyProperties(viewItem,selfShopDetailVO);
        return selfShopDetailVO;
    }

    @Override
    public ShopVO.DetailVO shopDetails(ShopDTO.IdDTO dto) {
        QueryWrapper<MerchantShopView> wrapper = MybatisPlusUtil.query();
        wrapper.eq("shop.id",dto.getId());
        MerchantShopView viewItem = shopMapper.getShopComplex(wrapper);
        if(null == viewItem){
            return null;
        }
        ShopVO.DetailVO detailVO = new ShopVO.DetailVO();
        BeanCopyUtils.copyProperties(viewItem,detailVO);

        QueryWrapper<ShopGoodsCategory> queryWrapperShopGoodsCategory = MybatisPlusUtil.query();
        queryWrapperShopGoodsCategory.eq("shop_id",viewItem.getId());
        List<ShopGoodsCategory> shopGoodsCategorieList = shopGoodsCategoryRepository.list(queryWrapperShopGoodsCategory);

        return detailVO;
    }

    @Override
    public void editorShop(ShopDTO.ComplexETO dto) {
        //查法人单位数据
        QueryWrapper<MerchantComplexView> queryWrapperBoost = MybatisPlusUtil.query();
        queryWrapperBoost.eq("shop.id",dto.getShopETO().getId());
        MerchantComplexView viewItem = merchantMapper.getMerchantComplex(queryWrapperBoost);
        String legalId = null;
        if(null == viewItem){
            LegalDictDTO.ETO addDTO = new LegalDictDTO.ETO();
            BeanCopyUtils.copyProperties(dto,addDTO);
            addDTO.setCertList(new ArrayList<>());
            for(LegalDictDTO.CertDTO certItem:dto.getLegalDTO().getCertList()){
                LegalDictDTO.CertDTO certDTO = new LegalDictDTO.CertDTO();
                certDTO.setId(certItem.getId());
                certDTO.setCertName(certItem.getCertName());
                certDTO.setCertUrl(certItem.getCertUrl());
                addDTO.getCertList().add(certDTO);
            }
            legalId =  legalDictRpc.addLegalDict(addDTO);
        }else{
            //更新法人单位数据
            legalId = viewItem.getLegalId();
            LegalDictDTO.ETO legalDictDTO = new LegalDictDTO.ETO();
            BeanCopyUtils.copyProperties(dto,legalDictDTO);
            legalDictDTO.setId(legalId);
            legalDictDTO.setCertList(new ArrayList<>());
            for(LegalDictDTO.CertDTO certItem:dto.getLegalDTO().getCertList()){
                LegalDictDTO.CertDTO certDTO = new LegalDictDTO.CertDTO();
                certDTO.setId(certItem.getId());
                certDTO.setCertName(certItem.getCertName());
                certDTO.setCertUrl(certItem.getCertUrl());
                legalDictDTO.getCertList().add(certDTO);
            }
            legalDictRpc.editLegalDict(legalDictDTO);
        }

        //更新店铺信息
        Shop shop = new Shop();
        BeanCopyUtils.copyProperties(dto, shop);
        repository.updateById(shop);
        //删除类目数据
        QueryWrapper<ShopGoodsCategory> queryWrapperDelete = MybatisPlusUtil.query();
        queryWrapperDelete.eq("shop_id",shop.getId());
        shopGoodsCategoryMapper.delete(queryWrapperDelete);

//        // 更新店铺经营类目
//        QueryWrapper<MerchantApplyCategory> merchantShopCategoryApplyQueryWrapper = MybatisPlusUtil.query();
//        merchantShopCategoryApplyQueryWrapper.in("goods_category_id",dto.getShopCategoryIds());
//        List<MerchantApplyCategory> merchantShopCategoryApplyList = merchantShopCategoryApplyRepository.frontRouterMap(merchantShopCategoryApplyQueryWrapper);

//        String [] goodsCategoryIdArr = dto.getShopETO().get.split(",");
//        List<ShopGoodsCategory> shopGoodsCategoryList = new ArrayList<>();
//        for(String goodsCategoryId:goodsCategoryIdArr){
//            ShopGoodsCategory shopGoodsCategory = new ShopGoodsCategory();
//            shopGoodsCategory.setShopId(shop.getId());
//            shopGoodsCategory.setCategoryId(goodsCategoryId);
//            shopGoodsCategoryList.add(shopGoodsCategory);
//        }
//        shopGoodsCategoryRepository.saveBatch(shopGoodsCategoryList);
    }

    @Override
    public List<ShopVO.InnerSimpleVO> innerlistShopIdName(BaseDTO dto, List<String> shopIdList) {
        if(ObjectUtils.isNotEmpty(shopIdList)){
            QueryWrapper<Shop> shopQueryWrapper = MybatisPlusUtil.query();
            shopQueryWrapper.in("id",shopIdList);
            List<Shop> list = repository.list(shopQueryWrapper);
            return ListUtil.listCover(ShopVO.InnerSimpleVO.class,list);
        }
        return null;
    }

    @Override
    public List<ShopVO.InnerSimpleVO> innerlistShopIdName(BaseDTO dto,Integer shopType,Integer terminal) {
        QueryWrapper<Shop> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("shop_type",shopType);
        List<Shop> shopList = repository.list(queryWrapper);
        return ListUtil.listCover(ShopVO.InnerSimpleVO.class,shopList);
    }

}
