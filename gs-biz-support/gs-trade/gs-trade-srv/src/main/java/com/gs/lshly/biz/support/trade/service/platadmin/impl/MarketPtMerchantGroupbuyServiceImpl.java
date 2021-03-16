package com.gs.lshly.biz.support.trade.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.trade.entity.*;
import com.gs.lshly.biz.support.trade.enums.PlatformCardCheckStatusEnum;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantGroupbuyGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantGroupbuyRepository;
import com.gs.lshly.biz.support.trade.service.platadmin.IMarketPtMerchantGroupbuyService;
import com.gs.lshly.common.enums.ActivitySignEnum;
import com.gs.lshly.common.enums.PlatformCardStatusEnum;
import com.gs.lshly.common.enums.ShopTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGroupbuyGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantGroupbuyQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantGroupbuyVO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.SkuGoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopDTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopVO;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsInfoRpc;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsInfoRpc;
import com.gs.lshly.rpc.api.platadmin.merchant.IShopRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MarketPtMerchantGroupbuyServiceImpl implements IMarketPtMerchantGroupbuyService {
    @Autowired
    private IMarketMerchantGroupbuyRepository repository;
    @Autowired
    private IMarketMerchantGroupbuyGoodsRepository iMarketMerchantGroupbuyGoodsRepository;
    @DubboReference
    private IPCMerchAdminGoodsInfoRpc ipcMerchAdminGoodsInfoRpc;
    @DubboReference
    private IShopRpc iShopRpc;
    @DubboReference
    private IGoodsInfoRpc iGoodsInfoRpc;
    @Override
    public PageData<PCMerchMarketMerchantGroupbuyVO.PlatformView> view(PCMerchMarketMerchantGroupbuyQTO.QTO qto) {
        QueryWrapper<MarketMerchantGroupbuy> wrapper = new QueryWrapper<>();
        wrapper.and(i->i.eq("is_commit",true));
        if (qto.getStatus()!=null){
            wrapper.eq("state",qto.getStatus());
        }
        List<MarketMerchantGroupbuy> list = repository.list(wrapper);
        if (ObjectUtils.isEmpty(list)){
            return new PageData<>();
        }
        List<PCMerchMarketMerchantGroupbuyVO.PlatformView> detailVOS = new ArrayList<>();
        Long now = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        for (MarketMerchantGroupbuy groupbuyList:list){
            Long end=groupbuyList.getValidEndTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
            PCMerchMarketMerchantGroupbuyVO.PlatformView detailVO = new PCMerchMarketMerchantGroupbuyVO.PlatformView();
            BeanUtils.copyProperties(groupbuyList,detailVO);
            ShopVO.DetailVO detailVO1=null;
            if (StringUtils.isNotBlank(groupbuyList.getShopId())) {
                detailVO1 = iShopRpc.shopDetails(new ShopDTO.IdDTO(groupbuyList.getShopId()));
            }
            if (ObjectUtils.isNotEmpty(detailVO1)) {
                detailVO.setShopName(detailVO1.getShopName());
            }
            //促销状态枚举类型[10=待审核 20=审核通过 30=已取消 40=未审核 50=审核拒绝]
            if (groupbuyList.getState().equals(PlatformCardCheckStatusEnum.拒审.getCode())){
                detailVO.setPromotionStatus(PlatformCardStatusEnum.审核拒绝.getCode());
                detailVOS.add(detailVO);
                continue;
            }
            //状态取消
            if (groupbuyList.getIsCancel()){
                detailVO.setPromotionStatus(PlatformCardStatusEnum.已取消.getCode());
                detailVOS.add(detailVO);
                continue;
            }
            if (now>=end && groupbuyList.getState().equals(PlatformCardStatusEnum.待审核.getCode())){
                detailVO.setPromotionStatus(PlatformCardStatusEnum.未审核.getCode());
                detailVOS.add(detailVO);
                continue;
            }
            //状态审核通过
            if ( groupbuyList.getState().equals(PlatformCardCheckStatusEnum.通过.getCode())){
                detailVO.setPromotionStatus(PlatformCardStatusEnum.审核通过.getCode());
                detailVOS.add(detailVO);
                continue;
            }
            if (groupbuyList.getIsCommit() && groupbuyList.getState().equals(PlatformCardStatusEnum.待审核.getCode())){
                detailVO.setPromotionStatus(PlatformCardStatusEnum.待审核.getCode());
                detailVOS.add(detailVO);
                continue;
            }
            detailVOS.add(detailVO);
        }
        return new PageData(detailVOS, qto.getPageNum(), qto.getPageSize(), detailVOS.size());
    }

    @Override
    public PCMerchMarketMerchantGroupbuyVO.PlatformCutView get(PCMerchMarketMerchantGroupbuyGoodsDTO.IdDTO id) {
        MarketMerchantGroupbuy marketMerchantGroupbuy = repository.getById(id.getId());
        PCMerchMarketMerchantGroupbuyVO.PlatformCutView platformView = new PCMerchMarketMerchantGroupbuyVO.PlatformCutView();
        BeanUtils.copyProperties(marketMerchantGroupbuy,platformView);
        ShopVO.DetailVO detailVO = iShopRpc.shopDetails(new ShopDTO.IdDTO(platformView.getShopId()));
        if (ObjectUtils.isNotEmpty(detailVO)) {
            platformView.setShopName(detailVO.getShopName() + " " + ShopTypeEnum.findRemark(detailVO.getShopType()));
        }
        List<PCMerchMarketMerchantGroupbuyVO.GoodsInfo> goodsInfos=new ArrayList<>();
        BigDecimal total=new BigDecimal(0);
        List<MarketMerchantGroupbuyGoods> marketMerchantGroupbuyGoods = SelectCartGoods(id.getId(), platformView.getMerchantId(), platformView.getShopId());
        if (ObjectUtils.isNotEmpty(marketMerchantGroupbuyGoods)) {
            for (MarketMerchantGroupbuyGoods goods : marketMerchantGroupbuyGoods) {
                PCMerchMarketMerchantGroupbuyVO.GoodsInfo goodsInfo = new PCMerchMarketMerchantGroupbuyVO.GoodsInfo();
                goodsInfo.setSalePrice(goods.getOriginalPrice()).
                        setGroupbuyPrice(goods.getGroupbuyPrice());
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
                    List<SkuGoodsInfoVO.ListVO> skuList = innerServiceGoodsInfoVO.getSkuList();
                    if (ObjectUtils.isNotEmpty(skuList)) {
                        List<String> arrSkuIdList=null;
                        if (ObjectUtils.isNotEmpty(goods.getSkuId())) {
                           arrSkuIdList = Arrays.asList(goods.getSkuId().split(","));
                        }
                        if (ObjectUtils.isNotEmpty(arrSkuIdList)) {
                            for (int i = 0; i < skuList.size(); i++) {
                                if (!arrSkuIdList.contains(skuList.get(i).getId())) {
                                    skuList.remove(i);
                                }
                            }
                        }
                        for (SkuGoodsInfoVO.ListVO goodsName : skuList) {
                            goodsInfo.setId(goodsName.getId());
                        }
                    }
                }
                total.add(ObjectUtils.isNotEmpty(goods.getGroupbuyPrice())?goods.getGroupbuyPrice():BigDecimal.ZERO);
                goodsInfos.add(goodsInfo);
            }
        }
        platformView.setGoodsInfos(goodsInfos).setTotalPrice(total);
        return platformView;
    }

    @Override
    public void check(PCMerchMarketMerchantGroupbuyGoodsDTO.Check dto) {
        MarketMerchantGroupbuy marketMerchantGroupbuy = repository.getById(dto.getId());
        if (dto.getPattern().equals(ActivitySignEnum.已审核.getCode()) && marketMerchantGroupbuy.getIsCommit()){
            marketMerchantGroupbuy.setState(PlatformCardCheckStatusEnum.通过.getCode());
        }
        if (dto.getPattern().equals(ActivitySignEnum.审核驳回.getCode() )&& marketMerchantGroupbuy.getIsCommit()){
            marketMerchantGroupbuy.setState(PlatformCardCheckStatusEnum.待审.getCode()).
                    setRevokeWhy(dto.getRevokeWhy());
        }
        repository.updateById(marketMerchantGroupbuy);
    }
    private List<MarketMerchantGroupbuyGoods> SelectCartGoods(String id, String jwtMerchantId, String jwtShopId) {
        QueryWrapper<MarketMerchantGroupbuyGoods> wrapper = new QueryWrapper<>();
        wrapper.eq("groupbuy_id",id).
                eq("merchant_id", jwtMerchantId).
                eq("shop_id", jwtShopId);
        return iMarketMerchantGroupbuyGoodsRepository.list(wrapper);

    }
}
