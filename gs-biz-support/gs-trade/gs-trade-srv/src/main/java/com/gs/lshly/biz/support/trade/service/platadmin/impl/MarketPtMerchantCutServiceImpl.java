package com.gs.lshly.biz.support.trade.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantCut;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantCutGoods;
import com.gs.lshly.biz.support.trade.enums.PlatformCardCheckStatusEnum;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantCutGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantCutRepository;
import com.gs.lshly.biz.support.trade.service.platadmin.IMarketPtMerchantCutService;
import com.gs.lshly.common.enums.ActivitySignEnum;
import com.gs.lshly.common.enums.PlatformCardStatusEnum;
import com.gs.lshly.common.enums.ShopTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchSkuGoodInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCutDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantCutQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCutVO;
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
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MarketPtMerchantCutServiceImpl implements IMarketPtMerchantCutService {
    @Autowired
    private IMarketMerchantCutRepository repository;
    @Autowired
    private IMarketMerchantCutGoodsRepository iMarketMerchantCutGoodsRepository;
    @DubboReference
    private IPCMerchAdminGoodsInfoRpc ipcMerchAdminGoodsInfoRpc;
    @DubboReference
    private IShopRpc iShopRpc;
    @DubboReference
    private IGoodsInfoRpc iGoodsInfoRpc;
    @Override
    public PageData<PCMerchMarketMerchantCutVO.PlatformView> view(PCMerchMarketMerchantCutQTO.QTO qto) {
        QueryWrapper<MarketMerchantCut> wrapper = new QueryWrapper<>();
        wrapper.and(i->i.eq("is_commit",true));
        if (qto.getStatus()!=null){
            wrapper.eq("state",qto.getStatus());
        }
        List<MarketMerchantCut> list = repository.list(wrapper);
        if (ObjectUtils.isEmpty(list)){
            return new PageData<>();
        }
        List<PCMerchMarketMerchantCutVO.PlatformView> detailVOS = new ArrayList<>();
        Long now = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        for (MarketMerchantCut cutList:list){
            Long end=cutList.getValidEndTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
            PCMerchMarketMerchantCutVO.PlatformView detailVO = new PCMerchMarketMerchantCutVO.PlatformView();
            BeanUtils.copyProperties(cutList,detailVO);
            ShopVO.DetailVO detailVO1 = iShopRpc.shopDetails(new ShopDTO.IdDTO(cutList.getShopId()));
            if (ObjectUtils.isNotEmpty(detailVO1)) {
                detailVO.setShopName(detailVO1.getShopName());
            }
            //促销状态枚举类型[10=待审核 20=审核通过 30=已取消 40=未审核 50=审核拒绝]
            if (cutList.getState().equals(PlatformCardCheckStatusEnum.拒审.getCode())){
                detailVO.setPromotionStatus(PlatformCardStatusEnum.审核拒绝.getCode());
                detailVOS.add(detailVO);
                continue;
            }
            //状态取消
            if (cutList.getIsCancel()){
                detailVO.setPromotionStatus(PlatformCardStatusEnum.已取消.getCode());
                detailVOS.add(detailVO);
                continue;
            }
            if (now>=end&&cutList.getState().equals(PlatformCardStatusEnum.待审核.getCode())){
                detailVO.setPromotionStatus(PlatformCardStatusEnum.未审核.getCode());
                detailVOS.add(detailVO);
                continue;
            }
            //状态审核通过
            if (cutList.getIsCommit()&& cutList.getState().equals(PlatformCardCheckStatusEnum.通过.getCode())){
                detailVO.setPromotionStatus(PlatformCardStatusEnum.审核通过.getCode());
                detailVOS.add(detailVO);
                continue;
            }
            if (cutList.getIsCommit()&&cutList.getState().equals(PlatformCardStatusEnum.待审核.getCode())){
                detailVO.setPromotionStatus(PlatformCardStatusEnum.待审核.getCode());
                detailVOS.add(detailVO);
                continue;
            }
            detailVOS.add(detailVO);
        }
        return new PageData(detailVOS, qto.getPageNum(), qto.getPageSize(), detailVOS.size());
    }

    @Override
    public PCMerchMarketMerchantCutVO.PlatformCutView get(PCMerchMarketMerchantCutDTO.IdDTO id) {
        MarketMerchantCut marketMerchantCut = repository.getById(id.getId());
        PCMerchMarketMerchantCutVO.PlatformCutView platformView = new PCMerchMarketMerchantCutVO.PlatformCutView();
        BeanUtils.copyProperties(marketMerchantCut,platformView);
        ShopVO.DetailVO detailVO = iShopRpc.shopDetails(new ShopDTO.IdDTO(platformView.getShopId()));
        if (ObjectUtils.isNotEmpty(detailVO)) {
            platformView.setShopName(detailVO.getShopName() + ShopTypeEnum.findRemark(detailVO.getShopType()));
        }
        List<PCMerchMarketMerchantCutVO.GoodsInfo> goodsInfos=new ArrayList<>();
        List<MarketMerchantCutGoods> marketMerchantCutGoods = SelectCartGoods(id.getId(), platformView.getMerchantId(), platformView.getShopId());
        if (ObjectUtils.isNotEmpty(marketMerchantCutGoods)) {
            for (MarketMerchantCutGoods goods :marketMerchantCutGoods) {
                PCMerchMarketMerchantCutVO.GoodsInfo goodsInfo = new PCMerchMarketMerchantCutVO.GoodsInfo();
                ArrayList<String> ids = new ArrayList<>();
                ids.add(goods.getGoodsId());
                List<GoodsInfoVO.InnerServiceGoodsInfoVO> innerServiceGoodsInfoVOS=null;
                if (ObjectUtils.isNotEmpty(ids)) {
                   innerServiceGoodsInfoVOS = iGoodsInfoRpc.innerServiceAllGoodsInfo(new GoodsInfoDTO.IdsInnerServiceDTO().setGoodsIdList(ids).setQueryType(10));
                }
                GoodsInfoVO.InnerServiceGoodsInfoVO innerServiceGoodsInfoVO=null;
                if (ObjectUtils.isNotEmpty(innerServiceGoodsInfoVOS)) {
                    innerServiceGoodsInfoVO  = innerServiceGoodsInfoVOS.get(0);
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
    public void check(PCMerchMarketMerchantCutDTO.Check dto) {
        MarketMerchantCut marketMerchantCut = repository.getById(dto.getId());
        if (dto.getPattern().equals(ActivitySignEnum.已审核.getCode()) && marketMerchantCut.getIsCommit()){
            marketMerchantCut.setState(PlatformCardCheckStatusEnum.通过.getCode());
        }
        if (dto.getPattern().equals(ActivitySignEnum.审核驳回.getCode()) && marketMerchantCut.getIsCommit()){
            marketMerchantCut.setState(PlatformCardCheckStatusEnum.拒审.getCode()).
                    setRevokeWhy(dto.getRevokeWhy());
        }
        repository.updateById(marketMerchantCut);
    }

    private List<MarketMerchantCutGoods> SelectCartGoods(String id, String jwtMerchantId, String jwtShopId) {
        QueryWrapper<MarketMerchantCutGoods> wrapper = new QueryWrapper<>();
        wrapper.eq("cut_id",id).
                eq("merchant_id", jwtMerchantId).
                eq("shop_id", jwtShopId);
        return iMarketMerchantCutGoodsRepository.list(wrapper);

    }
}
