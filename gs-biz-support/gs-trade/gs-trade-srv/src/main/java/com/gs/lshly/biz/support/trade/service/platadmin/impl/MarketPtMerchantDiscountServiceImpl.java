package com.gs.lshly.biz.support.trade.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.MarketCheck;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantCut;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantDiscount;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantDiscountGoods;
import com.gs.lshly.biz.support.trade.enums.PlatformCardCheckStatusEnum;
import com.gs.lshly.biz.support.trade.repository.IMarketCheckRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantDiscountGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantDiscountRepository;
import com.gs.lshly.biz.support.trade.service.platadmin.IMarketPtMerchantDiscountService;
import com.gs.lshly.common.enums.ActivitySignEnum;
import com.gs.lshly.common.enums.MarketCheckTypeEnum;
import com.gs.lshly.common.enums.PlatformCardStatusEnum;
import com.gs.lshly.common.enums.ShopTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantDiscountDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantDiscountQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCardVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantDiscountVO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.SkuGoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopDTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
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
import java.util.stream.Collectors;

@Component
public class MarketPtMerchantDiscountServiceImpl implements IMarketPtMerchantDiscountService {
    @Autowired
    private IMarketMerchantDiscountRepository repository;
    @Autowired
    private IMarketMerchantDiscountGoodsRepository iMarketMerchantDiscountGoodsRepository;
    @Autowired
    private IMarketCheckRepository iMarketCheckRepository;
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
        wrapper.orderByDesc("cdate");
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
        QueryWrapper<MarketCheck> query = MybatisPlusUtil.query();
        query.and(i->i.eq("activity_id",platformView.getId()));
        query.and(i->i.eq("check_type",MarketCheckTypeEnum.满折.getCode()));
        List<MarketCheck> list = iMarketCheckRepository.list(query);
        if (ObjectUtils.isNotEmpty(list)){
            List<PCMerchMarketMerchantCardVO.PlatformView.CheckVO> checkVOS =list.stream().map(e ->{
                PCMerchMarketMerchantCardVO.PlatformView.CheckVO checkVO = new PCMerchMarketMerchantCardVO.PlatformView.CheckVO();
                checkVO.setCheckDate(e.getCdate()).setCheckState(e.getCheckState()).setRemark(e.getRemark());
                return checkVO;
            }).collect(Collectors.toList());
            platformView.setCheckInfo(checkVOS);
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
                    goodsInfo.setSalePrice(innerServiceGoodsInfoVO.getSalePrice());
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
            if (marketMerchantCutDiscount.getState()==30){
                throw new BusinessException("不能审核");
            }
            saveCheck(marketMerchantCutDiscount,dto);
        }
        if (dto.getPattern().equals(ActivitySignEnum.审核驳回.getCode() )&& marketMerchantCutDiscount.getIsCommit()){
            marketMerchantCutDiscount.setState(PlatformCardCheckStatusEnum.拒审.getCode()).
                    setRevokeWhy(dto.getRevokeWhy()).setIsCommit(false);
            saveCheck(marketMerchantCutDiscount,dto);
        }
        repository.updateById(marketMerchantCutDiscount);
    }
    private void saveCheck(MarketMerchantDiscount marketMerchantCutDiscount,PCMerchMarketMerchantDiscountDTO.Check dto) {
        MarketCheck marketCheck = new MarketCheck();
        marketCheck.setCheckType(MarketCheckTypeEnum.满折.getCode()).
                setCheckState(marketMerchantCutDiscount.getState()).
                setActivityId(marketMerchantCutDiscount.getId()).setRemark(ObjectUtils.isNotEmpty(dto.getRevokeWhy())?dto.getRevokeWhy():"");
        iMarketCheckRepository.save(marketCheck);
    }
    private List<MarketMerchantDiscountGoods> SelectCartGoods(String id, String jwtMerchantId, String jwtShopId) {
        QueryWrapper<MarketMerchantDiscountGoods> wrapper = new QueryWrapper<>();
        wrapper.eq("discount_id",id).
                eq("merchant_id", jwtMerchantId).
                eq("shop_id", jwtShopId);
        return iMarketMerchantDiscountGoodsRepository.list(wrapper);

    }
}
