package com.gs.lshly.biz.support.trade.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantCard;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantCardGoods;
import com.gs.lshly.biz.support.trade.enums.PlatformCardCheckStatusEnum;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantCardGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantCardRepository;
import com.gs.lshly.biz.support.trade.service.platadmin.IMarketPtMerchantCardService;
import com.gs.lshly.common.enums.ActivitySignEnum;
import com.gs.lshly.common.enums.PlatformCardStatusEnum;
import com.gs.lshly.common.enums.ShopTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCardDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantCardQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCardGoodsVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCardVO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.SkuGoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopDTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopVO;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsInfoRpc;
import com.gs.lshly.rpc.api.platadmin.merchant.IShopRpc;
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
public class MarketPtMerchantCardServiceImpl implements IMarketPtMerchantCardService {
    @Autowired
    private IMarketMerchantCardRepository iMarketMerchantCardRepository;
    @Autowired
    private IMarketMerchantCardGoodsRepository iMarketMerchantCardGoodsRepository;
    @DubboReference
    private IShopRpc iShopRpc;
    @DubboReference
    private IGoodsInfoRpc iGoodsInfoRpc;
    @Override
    public PageData<PCMerchMarketMerchantCardVO.DetailVO> view(PCMerchMarketMerchantCardQTO.QTO qto) {
        QueryWrapper<MarketMerchantCard> wrapper = new QueryWrapper<>();
        wrapper.and(i->i.eq("is_commit",true));
        if (qto.getStatus()!=null){
            wrapper.eq("state",qto.getStatus());
        }
        List<MarketMerchantCard> list = iMarketMerchantCardRepository.list(wrapper);
        if (ObjectUtils.isEmpty(list)){
            return new PageData<>();
        }
        ArrayList<PCMerchMarketMerchantCardVO.DetailVO> detailVOS = new ArrayList<>();
        Long now = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        for (MarketMerchantCard cardList:list){
            Long start=cardList.getGetStartTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
            PCMerchMarketMerchantCardVO.DetailVO detailVO = new PCMerchMarketMerchantCardVO.DetailVO();
            BeanUtils.copyProperties(cardList,detailVO);
            ShopVO.DetailVO detailVO1=null;
            if (ObjectUtils.isNotEmpty(cardList.getShopId())) {
                detailVO1 = iShopRpc.shopDetails(new ShopDTO.IdDTO(cardList.getShopId()));
            }
            if (ObjectUtils.isNotEmpty(detailVO1)) {
                detailVO.setShopName(detailVO1.getShopName());
            }
                //促销状态枚举类型[10=待审核 20=审核通过 30=已取消 40=未审核 50=审核拒绝]
            if (cardList.getState().equals(PlatformCardCheckStatusEnum.拒审.getCode())){
                detailVO.setPromotionStatus(PlatformCardStatusEnum.审核拒绝.getCode());
                detailVOS.add(detailVO);
                continue;
            }
            //状态取消
            if (cardList.getIsCancel()){
                detailVO.setPromotionStatus(PlatformCardStatusEnum.已取消.getCode());
                detailVOS.add(detailVO);
                continue;
            }
            //状态审核通过
            if (cardList.getIsCommit()&& cardList.getState().equals(PlatformCardCheckStatusEnum.通过.getCode())){
                detailVO.setPromotionStatus(PlatformCardStatusEnum.审核通过.getCode());
                detailVOS.add(detailVO);
                continue;
            }
            if (now>=start&& cardList.getState().equals(PlatformCardCheckStatusEnum.通过.getCode())){
                detailVO.setPromotionStatus(PlatformCardStatusEnum.未审核.getCode());
                detailVOS.add(detailVO);
                continue;
            }
            if (cardList.getIsCommit()&& cardList.getState().equals(PlatformCardCheckStatusEnum.待审.getCode())){
                detailVO.setPromotionStatus(PlatformCardStatusEnum.待审核.getCode());
                detailVOS.add(detailVO);
                continue;
            }
            detailVOS.add(detailVO);
        }
        return new PageData(detailVOS, qto.getPageNum(), qto.getPageSize(), detailVOS.size());
    }

    @Override
    public PCMerchMarketMerchantCardVO.PlatformView get( PCMerchMarketMerchantCardDTO.IdDTO id) {
        MarketMerchantCard marketMerchantCard = iMarketMerchantCardRepository.getById(id.getId());
        PCMerchMarketMerchantCardVO.PlatformView platformView = new PCMerchMarketMerchantCardVO.PlatformView();
        BeanUtils.copyProperties(marketMerchantCard,platformView);
        ShopVO.DetailVO detailVO = iShopRpc.shopDetails(new ShopDTO.IdDTO(platformView.getShopId()));
        if (ObjectUtils.isNotEmpty(detailVO)) {
            platformView.setShopName(detailVO.getShopName() + ShopTypeEnum.findRemark(detailVO.getShopType()));
        }
        List<PCMerchMarketMerchantCardVO.GoodsInfo> goodsInfos=new ArrayList<>();
        List<MarketMerchantCardGoods> marketMerchantCardGoods = SelectCartGoods(id.getId(), platformView.getMerchantId(), platformView.getShopId());
        if (ObjectUtils.isNotEmpty(marketMerchantCardGoods)) {
            for (MarketMerchantCardGoods goods :marketMerchantCardGoods) {
                PCMerchMarketMerchantCardVO.GoodsInfo goodsInfo = new PCMerchMarketMerchantCardVO.GoodsInfo();
                ArrayList<String> ids = new ArrayList<>();
                List<GoodsInfoVO.InnerServiceGoodsInfoVO> innerServiceGoodsInfoVOS=null;
                ids.add(goods.getGoodsId());
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
                    if (ObjectUtils.isNotEmpty(goods.getSkuId())) {
                        arrSkuIdList= Arrays.asList(goods.getSkuId().split(","));
                    }
                    if (ObjectUtils.isNotEmpty(skuList) && ObjectUtils.isNotEmpty(arrSkuIdList)) {
                        for (int i = 0; i < skuList.size(); i++) {
                            if (!arrSkuIdList.contains(skuList.get(i).getId())) {
                                skuList.remove(i);
                            }
                        }
                    }
                    if (ObjectUtils.isNotEmpty(skuList)) {
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
    public void check(PCMerchMarketMerchantCardDTO.Check dto) {
        MarketMerchantCard marketMerchantCard = iMarketMerchantCardRepository.getById(dto.getId());
        if (dto.getPattern().equals(ActivitySignEnum.已审核.getCode() )&& marketMerchantCard.getIsCommit()){
            marketMerchantCard.setState(PlatformCardCheckStatusEnum.通过.getCode());
        }
        if (dto.getPattern().equals( ActivitySignEnum.审核驳回.getCode()) && marketMerchantCard.getIsCommit()){
            marketMerchantCard.setState(PlatformCardCheckStatusEnum.通过.getCode()).
                    setRevokeWhy(dto.getRevokeWhy());
        }
        iMarketMerchantCardRepository.updateById(marketMerchantCard);
    }

    /**
     * 通过优惠卷ID，店铺ID，商家ID，查询关联商品
     *
     **/
    private List<MarketMerchantCardGoods> SelectCartGoods(String id, String merchantId, String shopId) {
        QueryWrapper<MarketMerchantCardGoods> wrapper = new QueryWrapper<>();
        wrapper.eq("card_id",id);
        return iMarketMerchantCardGoodsRepository.list(wrapper);
    }
}
