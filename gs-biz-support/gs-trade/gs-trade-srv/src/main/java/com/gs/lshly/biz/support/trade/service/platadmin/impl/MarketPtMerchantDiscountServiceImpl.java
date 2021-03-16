package com.gs.lshly.biz.support.trade.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantDiscount;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantDiscountGoods;
import com.gs.lshly.biz.support.trade.enums.PlatformCardCheckStatusEnum;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantDiscountGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantDiscountRepository;
import com.gs.lshly.biz.support.trade.service.platadmin.IMarketPtMerchantDiscountService;
import com.gs.lshly.common.enums.ActivitySignEnum;
import com.gs.lshly.common.enums.PlatformCardStatusEnum;
import com.gs.lshly.common.enums.ShopTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantDiscountDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantDiscountQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantDiscountVO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.SkuGoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopDTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopVO;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsInfoRpc;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsInfoRpc;
import com.gs.lshly.rpc.api.platadmin.merchant.IShopRpc;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MarketPtMerchantDiscountServiceImpl implements IMarketPtMerchantDiscountService {
    @Autowired
    private IMarketMerchantDiscountRepository repository;
    @Autowired
    private IMarketMerchantDiscountGoodsRepository iMarketMerchantDiscountGoodsRepository;
    @DubboReference
    private IPCMerchAdminGoodsInfoRpc ipcMerchAdminGoodsInfoRpc;
    @DubboReference
    private IShopRpc iShopRpc;
    @DubboReference
    private IGoodsInfoRpc iGoodsInfoRpc;
    @Override
    public PageData<PCMerchMarketMerchantDiscountVO.PlatformView> view(PCMerchMarketMerchantDiscountQTO.QTO qto) {
        QueryWrapper<MarketMerchantDiscount> wrapper = new QueryWrapper<>();
        wrapper.and(i->i.eq("is_commit",true));
        if (qto.getStatus()!=null){
            wrapper.eq("state",qto.getStatus());
        }
        List<MarketMerchantDiscount> list = repository.list(wrapper);
        if (ObjectUtils.isEmpty(list)){
            return new PageData<>();
        }
        List<PCMerchMarketMerchantDiscountVO.PlatformView> detailVOS = new ArrayList<>();
        Long now = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        for (MarketMerchantDiscount discountList:list){
            Long end=discountList.getValidEndTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
            PCMerchMarketMerchantDiscountVO.PlatformView detailVO = new PCMerchMarketMerchantDiscountVO.PlatformView();
            BeanUtils.copyProperties(discountList,detailVO);
            ShopVO.DetailVO detailVO1=null;
            if (StringUtils.isNotBlank(discountList.getShopId())) {
                detailVO1 = iShopRpc.shopDetails(new ShopDTO.IdDTO(discountList.getShopId()));
            }
            if (ObjectUtils.isNotEmpty(detailVO1)) {
                detailVO.setShopName(detailVO1.getShopName());
            }
            //促销状态枚举类型[10=待审核 20=审核通过 30=已取消 40=未审核 50=审核拒绝]
            if (discountList.getState().equals(PlatformCardCheckStatusEnum.拒审.getCode())){
                detailVO.setPromotionStatus(PlatformCardStatusEnum.审核拒绝.getCode());
                detailVOS.add(detailVO);
                continue;
            }
            //状态取消
            if (discountList.getIsCancel()){
                detailVO.setPromotionStatus(PlatformCardStatusEnum.已取消.getCode());
                detailVOS.add(detailVO);
                continue;
            }
            if (now>=end && discountList.getState().equals(PlatformCardStatusEnum.待审核.getCode())){
                detailVO.setPromotionStatus(PlatformCardStatusEnum.未审核.getCode());
                detailVOS.add(detailVO);
                continue;
            }
            //状态审核通过
            if (discountList.getIsCommit()&& discountList.getState().equals(PlatformCardCheckStatusEnum.通过.getCode())){
                detailVO.setPromotionStatus(PlatformCardStatusEnum.审核通过.getCode());
                detailVOS.add(detailVO);
                continue;
            }
            if (discountList.getIsCommit() && discountList.getState().equals(PlatformCardStatusEnum.待审核.getCode())){
                detailVO.setPromotionStatus(PlatformCardStatusEnum.待审核.getCode());
                detailVOS.add(detailVO);
                continue;
            }
            detailVOS.add(detailVO);
        }
        return new PageData(detailVOS, qto.getPageNum(), qto.getPageSize(), detailVOS.size());
    }

    @Override
    public PCMerchMarketMerchantDiscountVO.PlatformCutView get( PCMerchMarketMerchantDiscountDTO.IdDTO id) {
        MarketMerchantDiscount marketMerchantDiscount = repository.getById(id.getId());
        PCMerchMarketMerchantDiscountVO.PlatformCutView platformView = new PCMerchMarketMerchantDiscountVO.PlatformCutView();
        BeanUtils.copyProperties(marketMerchantDiscount,platformView);
        ShopVO.DetailVO detailVO = iShopRpc.shopDetails(new ShopDTO.IdDTO(platformView.getShopId()));
        if (ObjectUtils.isNotEmpty(detailVO)) {
            platformView.setShopName(detailVO.getShopName() + ShopTypeEnum.findRemark(detailVO.getShopType()));
        }
        List<PCMerchMarketMerchantDiscountVO.GoodsInfo> goodsInfos=new ArrayList<>();
        List<MarketMerchantDiscountGoods> marketMerchantDiscountGoods = SelectCartGoods(id.getId(), platformView.getMerchantId(), platformView.getShopId());
        if (ObjectUtils.isNotEmpty(marketMerchantDiscountGoods)) {
            for (MarketMerchantDiscountGoods goods : marketMerchantDiscountGoods) {
                PCMerchMarketMerchantDiscountVO.GoodsInfo goodsInfo = new PCMerchMarketMerchantDiscountVO.GoodsInfo();
                ArrayList<String> ids = new ArrayList<>();
                ids.add(goods.getGoodsId());
                List<GoodsInfoVO.InnerServiceGoodsInfoVO> innerServiceGoodsInfoVOS=null;
                if (ObjectUtils.isNotEmpty(ids)) {
                    innerServiceGoodsInfoVOS = iGoodsInfoRpc.innerServiceAllGoodsInfo(new GoodsInfoDTO.IdsInnerServiceDTO().setGoodsIdList(ids).setQueryType(10));
                }
                GoodsInfoVO.InnerServiceGoodsInfoVO innerServiceGoodsInfoVO=null;
                if (ObjectUtils.isNotEmpty(innerServiceGoodsInfoVOS)) {
                   innerServiceGoodsInfoVO = innerServiceGoodsInfoVOS.get(0);
                }
                if (ObjectUtils.isNotEmpty(innerServiceGoodsInfoVO)) {
                    goodsInfo.setGoodsName(innerServiceGoodsInfoVO.getGoodsName());
                    List<SkuGoodsInfoVO.ListVO> skuList=null;
                    if (ObjectUtils.isNotEmpty(innerServiceGoodsInfoVO)) {
                        skuList = innerServiceGoodsInfoVO.getSkuList();
                    }
                    List<String> arrSkuIdList=null;
                    if (ObjectUtils.isNotEmpty(goods.getSkuIds())) {
                        arrSkuIdList= Arrays.asList(goods.getSkuIds().split(","));
                    }
                    if (ObjectUtils.isNotEmpty(skuList) && ObjectUtils.isNotEmpty(arrSkuIdList)) {
                        for (int i = 0; i < skuList.size(); i++) {
                            if (!arrSkuIdList.contains(skuList.get(i).getId())) {
                                skuList.remove(i);
                            }
                        }
                    }
                    if (ObjectUtils.isNotEmpty(skuList) ){
                        for (SkuGoodsInfoVO.ListVO goodsName : skuList) {
                            goodsInfo.setGoodsName(innerServiceGoodsInfoVO.getGoodsName() + " " + goodsName.getSpecsValue()).
                                    setId(goodsName.getId()).
                                    setSalePrice(goodsName.getSalePrice());
                        }
                    }
                }
                goodsInfos.add(goodsInfo);
            }
        }
        platformView.setGoodsInfos(goodsInfos);
        return platformView;
    }

    @Override
    public void check(PCMerchMarketMerchantDiscountDTO.Check dto) {
        MarketMerchantDiscount marketMerchantCutDiscount = repository.getById(dto.getId());
        if (dto.getPattern().equals(ActivitySignEnum.已审核.getCode()) && marketMerchantCutDiscount.getIsCommit()){
            marketMerchantCutDiscount.setState(PlatformCardCheckStatusEnum.通过.getCode());
        }
        if (dto.getPattern().equals(ActivitySignEnum.审核驳回.getCode() )&& marketMerchantCutDiscount.getIsCommit()){
            marketMerchantCutDiscount.setState(PlatformCardCheckStatusEnum.待审.getCode()).
                    setRevokeWhy(dto.getRevokeWhy());
        }
        repository.updateById(marketMerchantCutDiscount);
    }
    private List<MarketMerchantDiscountGoods> SelectCartGoods(String id, String jwtMerchantId, String jwtShopId) {
        QueryWrapper<MarketMerchantDiscountGoods> wrapper = new QueryWrapper<>();
        wrapper.eq("discount_id",id).
                eq("merchant_id", jwtMerchantId).
                eq("shop_id", jwtShopId);
        return iMarketMerchantDiscountGoodsRepository.list(wrapper);

    }
}
