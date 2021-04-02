package com.gs.lshly.biz.support.trade.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.*;
import com.gs.lshly.biz.support.trade.enums.PlatformCardCheckStatusEnum;
import com.gs.lshly.biz.support.trade.repository.IMarketCheckRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantGiftGoodsGiveRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantGiftGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantGiftRepository;
import com.gs.lshly.biz.support.trade.service.platadmin.IMarketPtMerchantGiftService;
import com.gs.lshly.common.enums.ActivitySignEnum;
import com.gs.lshly.common.enums.MarketCheckTypeEnum;
import com.gs.lshly.common.enums.PlatformCardStatusEnum;
import com.gs.lshly.common.enums.ShopTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchSkuGoodInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGiftDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantDiscountQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCardVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantGiftVO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.SkuGoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopDTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsInfoRpc;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsInfoRpc;
import com.gs.lshly.rpc.api.platadmin.merchant.IShopRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class MarketPtMerchantGiftServiceImpl implements IMarketPtMerchantGiftService {
    @Autowired
    private IMarketMerchantGiftRepository repository;
    @Autowired
    private IMarketMerchantGiftGoodsRepository iMarketMerchantGiftGoodsRepository;
    @Autowired
    private IMarketMerchantGiftGoodsGiveRepository iMarketMerchantGiftGoodsGiveRepository;
    @Autowired
    private IMarketCheckRepository iMarketCheckRepository;
    @DubboReference
    private IPCMerchAdminGoodsInfoRpc ipcMerchAdminGoodsInfoRpc;
    @DubboReference
    private IShopRpc iShopRpc;
    @DubboReference
    private IGoodsInfoRpc iGoodsInfoRpc;
    @Override
    public PageData<PCMerchMarketMerchantGiftVO.PlatformView> view(PCMerchMarketMerchantDiscountQTO.QTO qto) {
        QueryWrapper<MarketMerchantGift> wrapper = new QueryWrapper<>();
        wrapper.eq("is_commit",true);
        if (qto.getStatus()!=null){
            wrapper.eq("state",qto.getStatus());
        }
        wrapper.orderByDesc("cdate");
        List<MarketMerchantGift> list = repository.list(wrapper);
        if (ObjectUtils.isEmpty(list)){
            return new PageData<>();
        }
        List<PCMerchMarketMerchantGiftVO.PlatformView> detailVOS = new ArrayList<>();
        Long now = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        for (MarketMerchantGift giftList:list){
            Long end=giftList.getValidEndTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
            PCMerchMarketMerchantGiftVO.PlatformView detailVO = new PCMerchMarketMerchantGiftVO.PlatformView();
            BeanUtils.copyProperties(giftList,detailVO);
            ShopVO.DetailVO detailVO1=null;
            if (ObjectUtils.isNotEmpty(giftList.getShopId())) {
                detailVO1 = iShopRpc.shopDetails(new ShopDTO.IdDTO(giftList.getShopId()));
            }
            if (ObjectUtils.isNotEmpty(detailVO1)) {
                detailVO.setShopName(detailVO1.getShopName());
            }
            //促销状态枚举类型[10=待审核 20=审核通过 30=已取消 40=未审核 50=审核拒绝]
            if (giftList.getState().equals(PlatformCardCheckStatusEnum.拒审.getCode())){
                detailVO.setPromotionStatus(PlatformCardStatusEnum.审核拒绝.getCode());
                detailVOS.add(detailVO);
                continue;
            }
            //状态取消
            if (giftList.getIsCancel()){
                detailVO.setPromotionStatus(PlatformCardStatusEnum.已取消.getCode());
                detailVOS.add(detailVO);
                continue;
            }
            if (now>=end && giftList.getState().equals(PlatformCardStatusEnum.待审核.getCode())){
                detailVO.setPromotionStatus(PlatformCardStatusEnum.未审核.getCode());
                detailVOS.add(detailVO);
                continue;
            }
            //状态审核通过
            if (giftList.getIsCommit()&& giftList.getState().equals(PlatformCardCheckStatusEnum.通过.getCode())){
                detailVO.setPromotionStatus(PlatformCardStatusEnum.审核通过.getCode());
                detailVOS.add(detailVO);
                continue;
            }
            if (giftList.getIsCommit() && giftList.getState().equals(PlatformCardStatusEnum.待审核.getCode())){
                detailVO.setPromotionStatus(PlatformCardStatusEnum.待审核.getCode());
                detailVOS.add(detailVO);
                continue;
            }
            detailVOS.add(detailVO);
        }
        return new PageData(detailVOS, qto.getPageNum(), qto.getPageSize(), detailVOS.size());
    }

    @Override
    public PCMerchMarketMerchantGiftVO.PlatformCutView get(PCMerchMarketMerchantGiftDTO.IdDTO id) {
        MarketMerchantGift marketMerchantGift = repository.getById(id.getId());
        PCMerchMarketMerchantGiftVO.PlatformCutView platformView = new PCMerchMarketMerchantGiftVO.PlatformCutView();
        BeanUtils.copyProperties(marketMerchantGift,platformView);
        ShopVO.DetailVO detailVO = iShopRpc.shopDetails(new ShopDTO.IdDTO(platformView.getShopId()));
        if (ObjectUtils.isNotEmpty(detailVO)) {
            platformView.setShopName(detailVO.getShopName() + ShopTypeEnum.findRemark(detailVO.getShopType()));
        }
        QueryWrapper<MarketCheck> query = MybatisPlusUtil.query();
        query.and(i->i.eq("activity_id",platformView.getId()));
        query.and(i->i.eq("check_type",MarketCheckTypeEnum.满赠.getCode()));
        List<MarketCheck> list1 = iMarketCheckRepository.list(query);
        if (ObjectUtils.isNotEmpty(list1)){
            List<PCMerchMarketMerchantCardVO.PlatformView.CheckVO> checkVOS =list1.stream().map(e ->{
                PCMerchMarketMerchantCardVO.PlatformView.CheckVO checkVO = new PCMerchMarketMerchantCardVO.PlatformView.CheckVO();
                checkVO.setCheckDate(e.getCdate()).setCheckState(e.getCheckState()).setRemark(e.getRemark());
                return checkVO;
            }).collect(Collectors.toList());
            platformView.setCheckInfo(checkVOS);
        }
        List<PCMerchMarketMerchantGiftVO.GoodsInfo>  goodsInfos=new ArrayList<>();
        List<MarketMerchantGiftGoods> marketMerchantGiftGoods = SelectCartGoods(id.getId(), platformView.getMerchantId(), platformView.getShopId());
        if (ObjectUtils.isNotEmpty(marketMerchantGiftGoods)) {
            for (MarketMerchantGiftGoods goods : marketMerchantGiftGoods) {
                PCMerchMarketMerchantGiftVO.GoodsInfo goodsInfo = new PCMerchMarketMerchantGiftVO.GoodsInfo();
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
                    goodsInfo.setGoodsName(innerServiceGoodsInfoVO.getGoodsName()).setSalePrice(innerServiceGoodsInfoVO.getSalePrice());
                }
                goodsInfos.add(goodsInfo);
            }
        }
        List<PCMerchMarketMerchantGiftVO.GoodsGiveInfo> goodsGiveInfos=new ArrayList<>();
        //获取赠品
        QueryWrapper<MarketMerchantGiftGoodsGive> wrapper = new QueryWrapper<>();
        wrapper.eq("gift_id",marketMerchantGift.getId());
        List<MarketMerchantGiftGoodsGive> list = iMarketMerchantGiftGoodsGiveRepository.list(wrapper);
        Map<String,Integer> skuIds = new HashMap<>();
        if (ObjectUtils.isNotEmpty(list)) {
            for (MarketMerchantGiftGoodsGive marketMerchantGiftGoodsGive : list) {

                skuIds.put(marketMerchantGiftGoodsGive.getSkuId(), marketMerchantGiftGoodsGive.getTotal());
            }
        }
        //通过商品的SKU ID数组 查询 sku信息
        List<PCMerchSkuGoodInfoVO.ListVO> skuListInfo = ipcMerchAdminGoodsInfoRpc.innerServiceSkuGoodsList(new ArrayList<>(skuIds.keySet()));
        if (ObjectUtils.isNotEmpty(skuListInfo)) {
            for (PCMerchSkuGoodInfoVO.ListVO skuInfo : skuListInfo) {
                PCMerchMarketMerchantGiftVO.GoodsGiveInfo view2 = new PCMerchMarketMerchantGiftVO.GoodsGiveInfo();
                //通过商品ID查询商品信息
                ArrayList<String> goodsIds = new ArrayList<>();
                goodsIds.add(skuInfo.getGoodId());
                List<PCMerchGoodsInfoVO.InnerServiceGoodsVO> innerServiceGoodsVO = ipcMerchAdminGoodsInfoRpc.innerServiceGoodsVO(new PCMerchGoodsInfoDTO.IdsInnerServiceDTO().setGoodsIdList(goodsIds).setQueryType(10));
                PCMerchGoodsInfoVO.InnerServiceGoodsVO innerServiceGoodsVO1=null;
                if (ObjectUtils.isNotEmpty(innerServiceGoodsVO)){
                   innerServiceGoodsVO1 = innerServiceGoodsVO.get(0);
                }
                if (ObjectUtils.isNotEmpty(innerServiceGoodsVO1)){
                    view2.setGoodsGiveName(innerServiceGoodsVO1.getGoodsName() ).setId(innerServiceGoodsVO1.getId()).setTotal(skuIds.get(skuListInfo));
                    view2 .setId(innerServiceGoodsVO1.getId());
                }
                goodsGiveInfos.add(view2);
            }
        }
        platformView.setGoodsInfos(goodsInfos).
                setGiftView(goodsGiveInfos);
        return platformView;
    }

    @Override
    public void check(PCMerchMarketMerchantGiftDTO.Check dto) {
        MarketMerchantGift marketMerchantGift = repository.getById(dto.getId());
        if (dto.getPattern().equals(ActivitySignEnum.已审核.getCode()) && marketMerchantGift.getIsCommit()){
            marketMerchantGift.setState(PlatformCardCheckStatusEnum.通过.getCode());
            saveCheck(marketMerchantGift,dto);
        }
        if (dto.getPattern().equals(ActivitySignEnum.审核驳回.getCode() )&& marketMerchantGift.getIsCommit()){
            marketMerchantGift.setState(PlatformCardCheckStatusEnum.拒审.getCode()).setIsCommit(false).
                    setRevokeWhy(dto.getRevokeWhy());
            saveCheck(marketMerchantGift,dto);
        }
        repository.updateById(marketMerchantGift);
    }
    private void saveCheck(MarketMerchantGift marketMerchantGift,PCMerchMarketMerchantGiftDTO.Check dto) {
        MarketCheck marketCheck = new MarketCheck();
        marketCheck.setCheckType(MarketCheckTypeEnum.满赠.getCode()).
                setCheckState(marketMerchantGift.getState()).
                setActivityId(marketMerchantGift.getId()).setRemark(ObjectUtils.isNotEmpty(dto.getRevokeWhy())?dto.getRevokeWhy():"");
        iMarketCheckRepository.save(marketCheck);
    }
    private List<MarketMerchantGiftGoods> SelectCartGoods(String id, String jwtMerchantId, String jwtShopId) {
        QueryWrapper<MarketMerchantGiftGoods> wrapper = new QueryWrapper<>();
        wrapper.eq("gift_id",id).
                eq("merchant_id", jwtMerchantId).
                eq("shop_id", jwtShopId);
        return iMarketMerchantGiftGoodsRepository.list(wrapper);

    }
}
