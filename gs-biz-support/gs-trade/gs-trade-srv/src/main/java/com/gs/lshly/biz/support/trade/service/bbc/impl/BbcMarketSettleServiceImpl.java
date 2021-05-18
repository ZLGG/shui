package com.gs.lshly.biz.support.trade.service.bbc.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gs.lshly.biz.support.trade.service.bbb.pc.IPCBbbMarketMerchantCardUsersService;
import com.gs.lshly.biz.support.trade.service.bbc.IBbcMarketSettleService;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketMerchantCutGoodsService;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketMerchantDiscountGoodsService;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketMerchantGiftGoodsGiveService;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketMerchantGroupbuyGoodsService;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketPtActivityGoodsSkuService;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeBuildDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeGoodsDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeSettlementVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeBuildDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeGoodsDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeSettlementVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeGoodsDTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeSettlementVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeSettlementVO.ShopListVO;
import com.gs.lshly.common.struct.common.dto.CommonMarketDTO;
import com.gs.lshly.common.struct.common.vo.CommonMarketVO;
import com.gs.lshly.common.utils.JsonUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 订单营销
 * @author lxus
 */
@Component
@Slf4j
public class BbcMarketSettleServiceImpl implements IBbcMarketSettleService {

    @Autowired
    private IPCMerchMarketPtActivityGoodsSkuService ptActivityGoodsSkuService;

    @Autowired
    private IPCMerchMarketMerchantGroupbuyGoodsService groupbuyGoodsService;

    @Autowired
    private IPCMerchMarketMerchantCutGoodsService cutGoodsService;

    @Autowired
    private IPCMerchMarketMerchantDiscountGoodsService discountGoodsService;

    @Autowired
    private IPCMerchMarketMerchantGiftGoodsGiveService giftGoodsGiveService;

    @Autowired
    private IPCBbbMarketMerchantCardUsersService cardUsersService;

    private List<CommonMarketDTO.SkuId> getIds(List<BbcTradeSettlementVO.ShopListVO.goodsInfoVO> goodsInfoVOS) {
        List<CommonMarketDTO.SkuId> skuIds = new ArrayList<>();
        for (BbcTradeSettlementVO.ShopListVO.goodsInfoVO goods : goodsInfoVOS) {
            skuIds.add(new CommonMarketDTO.SkuId().setSkuId(goods.getSkuId()).setSpuId(goods.getGoodsId())
                    .setSkuSalePrice(goods.getSalePrice()).setSkuActivePrice(goods.getSalePrice()).setQuantity(goods.getQuantity()));
        }
        return skuIds;
    }


    private List<CommonMarketDTO.SkuId> getIds(Set<BbcTradeGoodsDTO.ETO> tradeGoodsDTOSet) {
        List<CommonMarketDTO.SkuId> skuIds = new ArrayList<>();
        for (BbcTradeGoodsDTO.ETO goods : tradeGoodsDTOSet) {
            skuIds.add(new CommonMarketDTO.SkuId().setSkuId(goods.getSkuId()).setSpuId(goods.getGoodsId())
                    .setSkuSalePrice(goods.getSalePrice()).setSkuActivePrice(goods.getSalePrice()).setQuantity(goods.getQuantity()));
        }
        return skuIds;
    }

    private List<CommonMarketDTO.SkuId> getIdsBBB(List<BbbTradeSettlementVO.ListVO.goodsInfoVO> goodsInfoVOS) {
        List<CommonMarketDTO.SkuId> skuIds = new ArrayList<>();
        for (BbbTradeSettlementVO.ListVO.goodsInfoVO goods : goodsInfoVOS) {
            skuIds.add(new CommonMarketDTO.SkuId().setSkuId(goods.getSkuId()).setSpuId(goods.getGoodsId())
                    .setSkuSalePrice(goods.getSalePrice()).setSkuActivePrice(goods.getSalePrice()).setQuantity(goods.getQuantity()));
        }
        return skuIds;
    }

    private List<CommonMarketDTO.SkuId> getIdsBBB(Set<BbbTradeGoodsDTO.ETO> tradeGoodsDTOSet) {
        List<CommonMarketDTO.SkuId> skuIds = new ArrayList<>();
        for (BbbTradeGoodsDTO.ETO goods : tradeGoodsDTOSet) {
            skuIds.add(new CommonMarketDTO.SkuId().setSkuId(goods.getSkuId()).setSpuId(goods.getGoodsId())
                    .setSkuSalePrice(goods.getSalePrice()).setSkuActivePrice(goods.getSalePrice()).setQuantity(goods.getQuantity()));
        }
        return skuIds;
    }

    private List<CommonMarketDTO.SkuId> getIdsBBBH5(List<BbbH5TradeSettlementVO.ListVO.goodsInfoVO> goodsInfoVOS) {
        List<CommonMarketDTO.SkuId> skuIds = new ArrayList<>();
        for (BbbH5TradeSettlementVO.ListVO.goodsInfoVO goods : goodsInfoVOS) {
            skuIds.add(new CommonMarketDTO.SkuId().setSkuId(goods.getSkuId()).setSpuId(goods.getGoodsId())
                    .setSkuSalePrice(goods.getSalePrice()).setSkuActivePrice(goods.getSalePrice()).setQuantity(goods.getQuantity()));
        }
        return skuIds;
    }

    private List<CommonMarketDTO.SkuId> getIdsBBBH5(Set<BbbH5TradeGoodsDTO.ETO> tradeGoodsDTOSet) {
        List<CommonMarketDTO.SkuId> skuIds = new ArrayList<>();
        for (BbbH5TradeGoodsDTO.ETO goods : tradeGoodsDTOSet) {
            skuIds.add(new CommonMarketDTO.SkuId().setSkuId(goods.getSkuId()).setSpuId(goods.getGoodsId())
                    .setSkuSalePrice(goods.getSalePrice()).setSkuActivePrice(goods.getSalePrice()).setQuantity(goods.getQuantity()));
        }
        return skuIds;
    }

    private CommonMarketDTO.MarketSku marge(List<CommonMarketDTO.SkuId> goods, CommonMarketDTO.MarketSku ptSkus, CommonMarketDTO.MarketSku groupSkus,
                                            CommonMarketDTO.MarketSku cutSkus, CommonMarketDTO.MarketSku discountSkus) {
        log.info("平台sku活动:{}", JsonUtils.toJson(ptSkus));
        log.info("团购sku活动:{}", JsonUtils.toJson(groupSkus));
        log.info("满减sku活动:{}", JsonUtils.toJson(cutSkus));
        log.info("满折sku活动:{}", JsonUtils.toJson(discountSkus));

        List<CommonMarketDTO.MarketSku> marketSkus = new ArrayList<>();
        if (ptSkus != null) {
            marketSkus.add(ptSkus);
        }
        if (groupSkus != null) {
            marketSkus.add(groupSkus);
        }
        if (cutSkus != null) {
            marketSkus.add(cutSkus);
        }
        if (discountSkus != null) {
            marketSkus.add(discountSkus);
        }

        return CommonMarketDTO.SkuId.calcBestMarketSku(marketSkus, goods, true);
    }

    private void calc(BbcTradeSettlementVO.ListVO settlementVO, CommonMarketDTO.MarketSku bestMarketSku,
                      BbcTradeSettlementVO.ListVO.goodsInfoVO giftGiveSku, CommonMarketDTO.MarketSku skuCards) {
        log.info("最优营销:{}", JsonUtils.toJson(bestMarketSku));
        log.info("满赠:{}", JsonUtils.toJson(giftGiveSku));
        log.info("优惠券:{}", JsonUtils.toJson(skuCards));
        if (bestMarketSku != null && !bestMarketSku.getSkuPrice().isEmpty()) {

            settlementVO.setMarketActiveVO(new CommonMarketVO.ActiveVO().setActiveName(bestMarketSku.getMarketName()));
            //设置sku活动价,并在总价基础上减去活动总价
            BigDecimal cutPriceRate = BigDecimal.ZERO;
            for (BbcTradeSettlementVO.ListVO.goodsInfoVO goods : settlementVO.getGoodsInfoVOS()) {
                if (goods.getGoodsId().equals(bestMarketSku.getSpuId())) {
                    BigDecimal skuPrice = bestMarketSku.getSkuPrice().get(goods.getSkuId());
                    if (skuPrice == null) {
                        continue;
                    }
                    goods.setActivePrice(skuPrice);
                    cutPriceRate = cutPriceRate.add(goods.getSalePrice().multiply(new BigDecimal("" + goods.getQuantity())));
                }
            }
            if (bestMarketSku.getMarketName().contains("减")
                    || bestMarketSku.getMarketName().contains("团购")
                    || bestMarketSku.getMarketName().contains("活动")) {
                settlementVO.setGoodsAmount(settlementVO.getGoodsAmount().subtract(bestMarketSku.getCutPrice()));
                settlementVO.getMarketActiveVO().setCutPrice(bestMarketSku.getCutPrice());
            }
            if (bestMarketSku.getMarketName().contains("折")) {
                BigDecimal cutPrice = cutPriceRate.multiply(BigDecimal.TEN.subtract(bestMarketSku.getCutPrice()).divide(BigDecimal.TEN, 2, RoundingMode.HALF_UP));
                settlementVO.setGoodsAmount(settlementVO.getGoodsAmount().subtract(cutPrice));
                settlementVO.getMarketActiveVO().setCutPrice(cutPrice);
            }
        }
        if (skuCards != null) {
            settlementVO.setUserCardVO(new CommonMarketVO.UserCardVO().setCardName(skuCards.getMarketName()).setCutPrice(skuCards.getCutPrice()).setCardId(skuCards.getCardId()));
            //合并优惠券与优惠价
            for (BbcTradeSettlementVO.ListVO.goodsInfoVO goods : settlementVO.getGoodsInfoVOS()) {
                if (goods.getGoodsId().equals(skuCards.getSpuId())) {
                    BigDecimal skuPrice = skuCards.getSkuPrice().get(goods.getSkuId());
                    if (skuPrice == null) {
                        continue;
                    }
                    //合并活动与优惠券价
                    skuPrice = goods.getActivePrice() == null ? skuPrice : goods.getActivePrice().subtract(goods.getSalePrice().subtract(skuPrice));
                    goods.setActivePrice(skuPrice);
                }
            }
            settlementVO.setGoodsAmount(settlementVO.getGoodsAmount().subtract(skuCards.getCutPrice()));
        }
        //todo 和总支付价格比较，小于支付价格，则直接加0.01
        for (BbcTradeSettlementVO.ListVO.goodsInfoVO goods : settlementVO.getGoodsInfoVOS()) {
            goods.setActivePrice(goods.getActivePrice() != null ? goods.getActivePrice().setScale(2, RoundingMode.HALF_UP) : goods.getSalePrice());
        }
        if (giftGiveSku != null) {
            giftGiveSku.setGoodsId("gift").setSkuId("gift");
            settlementVO.getGoodsInfoVOS().add(giftGiveSku);
        }
    }


    private void calcOrder(Set<BbcTradeGoodsDTO.ETO> tradeGoodsDTOSet, BbcTradeBuildDTO.DTO tradeGoodsDTO, CommonMarketDTO.MarketSku bestMarketSku,
                           BbcTradeGoodsDTO.ETO giftGiveSku, CommonMarketDTO.MarketSku skuCards) {
        /**
        
    	log.info("最优营销:{}", JsonUtils.toJson(bestMarketSku));
        log.info("满赠:{}", JsonUtils.toJson(giftGiveSku));
        log.info("优惠券:{}", JsonUtils.toJson(skuCards));
        if (bestMarketSku != null && !bestMarketSku.getSkuPrice().isEmpty()) {
            tradeGoodsDTO.setMarketActiveVO(new CommonMarketVO.ActiveVO().setActiveName(bestMarketSku.getMarketName()));
            //设置sku活动价,并在总价基础上减去活动总价
            BigDecimal cutPriceRate = BigDecimal.ZERO;
            for (BbcTradeGoodsDTO.ETO goods : tradeGoodsDTOSet) {
                if (goods.getGoodsId().equals(bestMarketSku.getSpuId())) {
                    BigDecimal skuPrice = bestMarketSku.getSkuPrice().get(goods.getSkuId());
                    if (skuPrice == null) {
                        continue;
                    }
                    goods.setDiscountAmount(goods.getSalePrice().subtract(skuPrice));//优惠金额=售价-活动价
                    goods.setPayAmount(goods.getSalePrice().subtract(goods.getDiscountAmount()));
                    cutPriceRate = cutPriceRate.add(goods.getSalePrice().multiply(new BigDecimal("" + goods.getQuantity())));
                }
            }
            if (bestMarketSku.getMarketName().contains("减")
                    || bestMarketSku.getMarketName().contains("团购")
                    || bestMarketSku.getMarketName().contains("活动")) {
                tradeGoodsDTO.setShopProductAmount(tradeGoodsDTO.getShopProductAmount().subtract(bestMarketSku.getCutPrice()));
                tradeGoodsDTO.getMarketActiveVO().setCutPrice(bestMarketSku.getCutPrice());
            }
            if (bestMarketSku.getMarketName().contains("折")) {
                BigDecimal cutPrice = cutPriceRate.multiply(BigDecimal.TEN.subtract(bestMarketSku.getCutPrice()).divide(BigDecimal.TEN, 2, RoundingMode.HALF_UP));
                tradeGoodsDTO.setShopProductAmount(tradeGoodsDTO.getShopProductAmount().subtract(cutPrice));
                tradeGoodsDTO.getMarketActiveVO().setCutPrice(cutPrice);
            }
        }
        if (skuCards != null) {
            tradeGoodsDTO.setUserCardVO(new CommonMarketVO.UserCardVO().setCardName(skuCards.getMarketName()).setCutPrice(skuCards.getCutPrice()).setCardId(skuCards.getCardId()));
            //合并优惠券与优惠价
            for (BbcTradeGoodsDTO.ETO goods : tradeGoodsDTOSet) {
                if (goods.getGoodsId().equals(skuCards.getSpuId())) {
                    BigDecimal skuPrice = skuCards.getSkuPrice().get(goods.getSkuId());
                    if (skuPrice == null) {
                        continue;
                    }
                    //合并优惠券价
                    BigDecimal discountPrice = goods.getSalePrice().subtract(skuPrice).add(goods.getDiscountAmount() != null ? goods.getDiscountAmount() : BigDecimal.ZERO);
                    goods.setDiscountAmount(discountPrice);
                    goods.setPayAmount(goods.getSalePrice().subtract(goods.getDiscountAmount()));
                }
            }
            tradeGoodsDTO.setShopProductAmount(tradeGoodsDTO.getShopProductAmount().subtract(skuCards.getCutPrice()));
        }
        //todo 和总支付价格比较，小于支付价格，则直接加0.01
        for (BbcTradeGoodsDTO.ETO goods : tradeGoodsDTOSet) {
            goods.setPayAmount(goods.getPayAmount() != null ? goods.getPayAmount().setScale(2, RoundingMode.HALF_UP) : goods.getSalePrice());
        }
        if (giftGiveSku != null) {
            tradeGoodsDTOSet.add(giftGiveSku);
        }**/
    }

    @Override
    public void settlement(BbcTradeSettlementVO.DetailVO settlementVO, BbcTradeBuildDTO.cartIdsDTO dto) {
    	List<BbcTradeSettlementVO.ShopListVO.goodsInfoVO> goodsInfoVOS = new ArrayList<BbcTradeSettlementVO.ShopListVO.goodsInfoVO>();
    	
    	List<ShopListVO> shopListVO = settlementVO.getShopList();
    	for(ShopListVO shoplistvo :shopListVO){
    		List<BbcTradeSettlementVO.ShopListVO.goodsInfoVO> goodsinfovos = shoplistvo.getGoodsInfoVOS();
    		goodsInfoVOS.containsAll(goodsinfovos);
    	}
    	
        List<CommonMarketDTO.SkuId> skuIds = getIds(goodsInfoVOS);
        //1,营销活动与购买产品的对应关系-MarketSku
        //1.1平台活动, 匹配到skuId
        CommonMarketDTO.MarketSku ptSkus =  ptActivityGoodsSkuService.activePtActivitySku(skuIds, dto.getTerminal());
        //1.2团购, 匹配到skuId
        CommonMarketDTO.MarketSku groupSkus = groupbuyGoodsService.activeGroupbuySku(skuIds, dto.getTerminal());
        //1.3满减, 匹配到spuId, 同一spu合并-ok
        CommonMarketDTO.MarketSku cutSkus = cutGoodsService.activeCutPriceSku(skuIds, dto.getTerminal());
        //1.4满折, 匹配到spuId, 同一spu合并-ok
        CommonMarketDTO.MarketSku discountSkus = discountGoodsService.activeDiscountPriceSku(skuIds, dto.getTerminal());
        //1.5各sku活动合并后的最低价, 以上营销互斥 - ok
        CommonMarketDTO.MarketSku margeSkus = marge(skuIds, ptSkus, groupSkus, cutSkus, discountSkus);
        log.info("合并营销活动：{}", JsonUtils.toJson(skuIds));

        //2,购买产品与持有优惠券的对应关系-SkuCard, 同一spu合并 -ok
        CommonMarketDTO.MarketSku skuCards = cardUsersService.activeCardSku(dto.getJwtUserId(), skuIds, dto.getTerminal());

        //1.5满赠, 匹配到spuId, 同一spu合并-ok
        CommonMarketDTO.SkuId giveSkuId = giftGoodsGiveService.activeGiveSku(skuIds, dto.getTerminal());
        BbcTradeSettlementVO.ListVO.goodsInfoVO giftGiveSku = giftGoodsGiveService.fillBbcGoodsInfoSettlementVO(giveSkuId);

        //3,产品原价及合并后的sku活动价加上sku优惠券,并附上赠品,合并计算 YINGJUN
//        calc(settlementVO, margeSkus, giftGiveSku, skuCards);

    }

    @Override
    public void settlement(Set<BbcTradeGoodsDTO.ETO> tradeGoodsDTOSet, BbcTradeBuildDTO.DTO dto) {
/**
        List<CommonMarketDTO.SkuId> skuIds = getIds(tradeGoodsDTOSet);

        //1,营销活动与购买产品的对应关系-MarketSku
        //1.1平台活动, 匹配到skuId
        CommonMarketDTO.MarketSku ptSkus =  ptActivityGoodsSkuService.activePtActivitySku(skuIds, dto.getTerminal());
        //1.2团购, 匹配到skuId
        CommonMarketDTO.MarketSku groupSkus = groupbuyGoodsService.activeGroupbuySku(skuIds, dto.getTerminal());
        //1.3满减, 匹配到spuId, 同一spu合并-ok
        CommonMarketDTO.MarketSku cutSkus = cutGoodsService.activeCutPriceSku(skuIds, dto.getTerminal());
        //1.4满折, 匹配到spuId, 同一spu合并-ok
        CommonMarketDTO.MarketSku discountSkus = discountGoodsService.activeDiscountPriceSku(skuIds, dto.getTerminal());
        //1.5各sku活动合并后的最低价, 以上营销互斥 - ok
        CommonMarketDTO.MarketSku margeSkus = marge(skuIds, ptSkus, groupSkus, cutSkus, discountSkus);
        log.info("合并营销活动：{}", JsonUtils.toJson(skuIds));

        //2,购买产品与持有优惠券的对应关系-SkuCard, 同一spu合并 -ok
        CommonMarketDTO.MarketSku skuCards = cardUsersService.activeCardSku(dto.getJwtUserId(), skuIds, dto.getTerminal());

        //1.5满赠, 匹配到spuId, 同一spu合并-ok  TODO
        CommonMarketDTO.SkuId giveSkuId = giftGoodsGiveService.activeGiveSku(skuIds, dto.getTerminal());
        BbcTradeGoodsDTO.ETO giftGiveSku = giftGoodsGiveService.fillBbcGoodsInfoOrderVO(giveSkuId, dto);

        //3,产品原价及合并后的sku活动价加上sku优惠券,并附上赠品,合并计算
        calcOrder(tradeGoodsDTOSet, dto, margeSkus, giftGiveSku, skuCards);**/
    }


    private void calcBBB(BbbTradeSettlementVO.ListVO settlementVO, CommonMarketDTO.MarketSku bestMarketSku,
                      BbbTradeSettlementVO.ListVO.goodsInfoVO giftGiveSku, CommonMarketDTO.MarketSku skuCards) {
        log.info("最优营销:{}", JsonUtils.toJson(bestMarketSku));
        log.info("满赠:{}", JsonUtils.toJson(giftGiveSku));
        log.info("优惠券:{}", JsonUtils.toJson(skuCards));
        if (bestMarketSku != null && !bestMarketSku.getSkuPrice().isEmpty()) {

            settlementVO.setMarketActiveVO(new CommonMarketVO.ActiveVO().setActiveName(bestMarketSku.getMarketName()));
            //设置sku活动价,并在总价基础上减去活动总价
            BigDecimal cutPriceRate = BigDecimal.ZERO;
            for (BbbTradeSettlementVO.ListVO.goodsInfoVO goods : settlementVO.getGoodsInfoVOS()) {
                if (goods.getGoodsId().equals(bestMarketSku.getSpuId())) {
                    BigDecimal skuPrice = bestMarketSku.getSkuPrice().get(goods.getSkuId());
                    if (skuPrice == null) {
                        continue;
                    }
                    goods.setActivePrice(skuPrice);
                    cutPriceRate = cutPriceRate.add(goods.getSalePrice().multiply(new BigDecimal("" + goods.getQuantity())));
                }
            }
            if (bestMarketSku.getMarketName().contains("减")
                    || bestMarketSku.getMarketName().contains("团购")
                    || bestMarketSku.getMarketName().contains("活动")) {
                settlementVO.setGoodsAmount(settlementVO.getGoodsAmount().subtract(bestMarketSku.getCutPrice()));
                settlementVO.getMarketActiveVO().setCutPrice(bestMarketSku.getCutPrice());
            }
            if (bestMarketSku.getMarketName().contains("折")) {
                BigDecimal cutPrice = cutPriceRate.multiply(BigDecimal.TEN.subtract(bestMarketSku.getCutPrice()).divide(BigDecimal.TEN, 2, RoundingMode.HALF_UP));
                settlementVO.setGoodsAmount(settlementVO.getGoodsAmount().subtract(cutPrice));
                settlementVO.getMarketActiveVO().setCutPrice(cutPrice);
            }
        }
        if (skuCards != null) {
            settlementVO.setUserCardVO(new CommonMarketVO.UserCardVO().setCardName(skuCards.getMarketName()).setCutPrice(skuCards.getCutPrice()).setCardId(skuCards.getCardId()));
            //合并优惠券与优惠价
            for (BbbTradeSettlementVO.ListVO.goodsInfoVO goods : settlementVO.getGoodsInfoVOS()) {
                if (goods.getGoodsId().equals(skuCards.getSpuId())) {
                    BigDecimal skuPrice = skuCards.getSkuPrice().get(goods.getSkuId());
                    if (skuPrice == null) {
                        continue;
                    }
                    //合并活动与优惠券价
                    skuPrice = goods.getActivePrice() == null ? skuPrice : goods.getActivePrice().subtract(goods.getSalePrice().subtract(skuPrice));
                    goods.setActivePrice(skuPrice);
                }
            }
            settlementVO.setGoodsAmount(settlementVO.getGoodsAmount().subtract(skuCards.getCutPrice()));
        }
        //todo 和总支付价格比较，小于支付价格，则直接加0.01
        for (BbbTradeSettlementVO.ListVO.goodsInfoVO goods : settlementVO.getGoodsInfoVOS()) {
            goods.setActivePrice(goods.getActivePrice() != null ? goods.getActivePrice().setScale(2, RoundingMode.HALF_UP) : goods.getSalePrice());
        }
        if (giftGiveSku != null) {
            giftGiveSku.setGoodsId("gift").setSkuId("gift");
            settlementVO.getGoodsInfoVOS().add(giftGiveSku);
        }
    }

    private void calcOrderBBB(Set<BbbTradeGoodsDTO.ETO> tradeGoodsDTOSet, BbbTradeBuildDTO.DTO tradeGoodsDTO,
                              CommonMarketDTO.MarketSku bestMarketSku, BbbTradeGoodsDTO.ETO giftGiveSku,
                              CommonMarketDTO.MarketSku skuCards) {
        log.info("最优营销:{}", JsonUtils.toJson(bestMarketSku));
        log.info("满赠:{}", JsonUtils.toJson(giftGiveSku));
        log.info("优惠券:{}", JsonUtils.toJson(skuCards));
        if (bestMarketSku != null && !bestMarketSku.getSkuPrice().isEmpty()) {
            tradeGoodsDTO.setMarketActiveVO(new CommonMarketVO.ActiveVO().setActiveName(bestMarketSku.getMarketName()));
            //设置sku活动价,并在总价基础上减去活动总价
            BigDecimal cutPriceRate = BigDecimal.ZERO;
            for (BbbTradeGoodsDTO.ETO goods : tradeGoodsDTOSet) {
                if (goods.getGoodsId().equals(bestMarketSku.getSpuId())) {
                    BigDecimal skuPrice = bestMarketSku.getSkuPrice().get(goods.getSkuId());
                    if (skuPrice == null) {
                        continue;
                    }
                    goods.setDiscountAmount(goods.getSalePrice().subtract(skuPrice));//优惠金额=售价-活动价
                    goods.setPayAmount(goods.getSalePrice().subtract(goods.getDiscountAmount()));
                    cutPriceRate = cutPriceRate.add(goods.getSalePrice().multiply(new BigDecimal("" + goods.getQuantity())));
                }
            }
            if (bestMarketSku.getMarketName().contains("减")
                    || bestMarketSku.getMarketName().contains("团购")
                    || bestMarketSku.getMarketName().contains("活动")) {
                tradeGoodsDTO.setShopProductAmount(tradeGoodsDTO.getShopProductAmount().subtract(bestMarketSku.getCutPrice()));
                tradeGoodsDTO.getMarketActiveVO().setCutPrice(bestMarketSku.getCutPrice());
            }
            if (bestMarketSku.getMarketName().contains("折")) {
                BigDecimal cutPrice = cutPriceRate.multiply(BigDecimal.TEN.subtract(bestMarketSku.getCutPrice()).divide(BigDecimal.TEN, 2, RoundingMode.HALF_UP));
                tradeGoodsDTO.setShopProductAmount(tradeGoodsDTO.getShopProductAmount().subtract(cutPrice));
                tradeGoodsDTO.getMarketActiveVO().setCutPrice(cutPrice);
            }
        }
        if (skuCards != null) {
            tradeGoodsDTO.setUserCardVO(new CommonMarketVO.UserCardVO().setCardName(skuCards.getMarketName()).setCutPrice(skuCards.getCutPrice()).setCardId(skuCards.getCardId()));
            //合并优惠券与优惠价
            for (BbbTradeGoodsDTO.ETO goods : tradeGoodsDTOSet) {
                if (goods.getGoodsId().equals(skuCards.getSpuId())) {
                    BigDecimal skuPrice = skuCards.getSkuPrice().get(goods.getSkuId());
                    if (skuPrice == null) {
                        continue;
                    }
                    //合并优惠券价
                    BigDecimal discountPrice = goods.getSalePrice().subtract(skuPrice).add(goods.getDiscountAmount() != null ? goods.getDiscountAmount() : BigDecimal.ZERO);
                    goods.setDiscountAmount(discountPrice);
                    goods.setPayAmount(goods.getSalePrice().subtract(goods.getDiscountAmount()));
                }
            }
            tradeGoodsDTO.setShopProductAmount(tradeGoodsDTO.getShopProductAmount().subtract(skuCards.getCutPrice()));
        }
        //todo 和总支付价格比较，小于支付价格，则直接加0.01
        for (BbbTradeGoodsDTO.ETO goods : tradeGoodsDTOSet) {
            goods.setPayAmount(goods.getPayAmount() != null ? goods.getPayAmount().setScale(2, RoundingMode.HALF_UP) : goods.getSalePrice());
        }
        if (giftGiveSku != null) {
            tradeGoodsDTOSet.add(giftGiveSku);
        }
    }

    @Override
    public void settlementPCBBB(BbbTradeSettlementVO.ListVO settlementVO, BbbTradeBuildDTO.cartIdsDTO dto) {

        List<CommonMarketDTO.SkuId> skuIds = getIdsBBB(settlementVO.getGoodsInfoVOS());
        //1,营销活动与购买产品的对应关系-MarketSku
        //1.1平台活动, 匹配到skuId
        CommonMarketDTO.MarketSku ptSkus =  ptActivityGoodsSkuService.activePtActivitySku(skuIds, dto.getTerminal());
        //1.2团购, 匹配到skuId
        CommonMarketDTO.MarketSku groupSkus = groupbuyGoodsService.activeGroupbuySku(skuIds, dto.getTerminal());
        //1.3满减, 匹配到spuId, 同一spu合并-ok
        CommonMarketDTO.MarketSku cutSkus = cutGoodsService.activeCutPriceSku(skuIds, dto.getTerminal());
        //1.4满折, 匹配到spuId, 同一spu合并-ok
        CommonMarketDTO.MarketSku discountSkus = discountGoodsService.activeDiscountPriceSku(skuIds, dto.getTerminal());
        //1.5各sku活动合并后的最低价, 以上营销互斥 - ok
        CommonMarketDTO.MarketSku margeSkus = marge(skuIds, ptSkus, groupSkus, cutSkus, discountSkus);
        log.info("合并营销活动：{}", JsonUtils.toJson(skuIds));

        //2,购买产品与持有优惠券的对应关系-SkuCard, 同一spu合并 -ok
        CommonMarketDTO.MarketSku skuCards = cardUsersService.activeCardSku(dto.getJwtUserId(), skuIds, dto.getTerminal());

        //1.5满赠, 匹配到spuId, 同一spu合并-ok
        CommonMarketDTO.SkuId giveSkuId = giftGoodsGiveService.activeGiveSku(skuIds, dto.getTerminal());
        BbbTradeSettlementVO.ListVO.goodsInfoVO giftGiveSku = giftGoodsGiveService.fillBbcGoodsInfoSettlementVOBBB(giveSkuId, dto);

        //3,产品原价及合并后的sku活动价加上sku优惠券,并附上赠品,合并计算
        calcBBB(settlementVO, margeSkus, giftGiveSku, skuCards);
    }


    @Override
    public void settlementPCBBB(Set<BbbTradeGoodsDTO.ETO> tradeGoodsDTOSet, BbbTradeBuildDTO.DTO dto) {
        List<CommonMarketDTO.SkuId> skuIds = getIdsBBB(tradeGoodsDTOSet);

        //1,营销活动与购买产品的对应关系-MarketSku
        //1.1平台活动, 匹配到skuId
        CommonMarketDTO.MarketSku ptSkus =  ptActivityGoodsSkuService.activePtActivitySku(skuIds, dto.getTerminal());
        //1.2团购, 匹配到skuId
        CommonMarketDTO.MarketSku groupSkus = groupbuyGoodsService.activeGroupbuySku(skuIds, dto.getTerminal());
        //1.3满减, 匹配到spuId, 同一spu合并-ok
        CommonMarketDTO.MarketSku cutSkus = cutGoodsService.activeCutPriceSku(skuIds, dto.getTerminal());
        //1.4满折, 匹配到spuId, 同一spu合并-ok
        CommonMarketDTO.MarketSku discountSkus = discountGoodsService.activeDiscountPriceSku(skuIds, dto.getTerminal());
        //1.5各sku活动合并后的最低价, 以上营销互斥 - ok
        CommonMarketDTO.MarketSku margeSkus = marge(skuIds, ptSkus, groupSkus, cutSkus, discountSkus);
        log.info("合并营销活动：{}", JsonUtils.toJson(skuIds));

        //2,购买产品与持有优惠券的对应关系-SkuCard, 同一spu合并 -ok
        CommonMarketDTO.MarketSku skuCards = cardUsersService.activeCardSku(dto.getJwtUserId(), skuIds, dto.getTerminal());

        //1.5满赠, 匹配到spuId, 同一spu合并-ok
        CommonMarketDTO.SkuId giveSkuId = giftGoodsGiveService.activeGiveSku(skuIds, dto.getTerminal());
        BbbTradeGoodsDTO.ETO giftGiveSku = giftGoodsGiveService.fillBbcGoodsInfoOrderVOBBB(giveSkuId, dto);

        //3,产品原价及合并后的sku活动价加上sku优惠券,并附上赠品,合并计算
        calcOrderBBB(tradeGoodsDTOSet, dto, margeSkus, giftGiveSku, skuCards);
    }

    private void calcBBBH5(BbbH5TradeSettlementVO.ListVO settlementVO, CommonMarketDTO.MarketSku bestMarketSku,
                         BbbH5TradeSettlementVO.ListVO.goodsInfoVO giftGiveSku, CommonMarketDTO.MarketSku skuCards) {
        log.info("最优营销:{}", JsonUtils.toJson(bestMarketSku));
        log.info("满赠:{}", JsonUtils.toJson(giftGiveSku));
        log.info("优惠券:{}", JsonUtils.toJson(skuCards));
        if (bestMarketSku != null && !bestMarketSku.getSkuPrice().isEmpty()) {

            settlementVO.setMarketActiveVO(new CommonMarketVO.ActiveVO().setActiveName(bestMarketSku.getMarketName()));
            //设置sku活动价,并在总价基础上减去活动总价
            BigDecimal cutPriceRate = BigDecimal.ZERO;
            for (BbbH5TradeSettlementVO.ListVO.goodsInfoVO goods : settlementVO.getGoodsInfoVOS()) {
                if (goods.getGoodsId().equals(bestMarketSku.getSpuId())) {
                    BigDecimal skuPrice = bestMarketSku.getSkuPrice().get(goods.getSkuId());
                    if (skuPrice == null) {
                        continue;
                    }
                    goods.setActivePrice(skuPrice);
                    cutPriceRate = cutPriceRate.add(goods.getSalePrice().multiply(new BigDecimal("" + goods.getQuantity())));
                }
            }
            if (bestMarketSku.getMarketName().contains("减")
                    || bestMarketSku.getMarketName().contains("团购")
                    || bestMarketSku.getMarketName().contains("活动")) {
                settlementVO.setGoodsAmount(settlementVO.getGoodsAmount().subtract(bestMarketSku.getCutPrice()));
                settlementVO.getMarketActiveVO().setCutPrice(bestMarketSku.getCutPrice());
            }
            if (bestMarketSku.getMarketName().contains("折")) {
                BigDecimal cutPrice = cutPriceRate.multiply(BigDecimal.TEN.subtract(bestMarketSku.getCutPrice()).divide(BigDecimal.TEN, 2, RoundingMode.HALF_UP));
                settlementVO.setGoodsAmount(settlementVO.getGoodsAmount().subtract(cutPrice));
                settlementVO.getMarketActiveVO().setCutPrice(cutPrice);
            }
        }
        if (skuCards != null) {
            settlementVO.setUserCardVO(new CommonMarketVO.UserCardVO().setCardName(skuCards.getMarketName()).setCutPrice(skuCards.getCutPrice()).setCardId(skuCards.getCardId()));
            //合并优惠券与优惠价
            for (BbbH5TradeSettlementVO.ListVO.goodsInfoVO goods : settlementVO.getGoodsInfoVOS()) {
                if (goods.getGoodsId().equals(skuCards.getSpuId())) {
                    BigDecimal skuPrice = skuCards.getSkuPrice().get(goods.getSkuId());
                    if (skuPrice == null) {
                        continue;
                    }
                    //合并活动与优惠券价
                    skuPrice = goods.getActivePrice() == null ? skuPrice : goods.getActivePrice().subtract(goods.getSalePrice().subtract(skuPrice));
                    goods.setActivePrice(skuPrice);
                }
            }
            settlementVO.setGoodsAmount(settlementVO.getGoodsAmount().subtract(skuCards.getCutPrice()));
        }
        //todo 和总支付价格比较，小于支付价格，则直接加0.01
        for (BbbH5TradeSettlementVO.ListVO.goodsInfoVO goods : settlementVO.getGoodsInfoVOS()) {
            goods.setActivePrice(goods.getActivePrice() != null ? goods.getActivePrice().setScale(2, RoundingMode.HALF_UP) : goods.getSalePrice());
        }

        if (giftGiveSku != null) {
            giftGiveSku.setGoodsId("gift").setSkuId("gift");
            settlementVO.getGoodsInfoVOS().add(giftGiveSku);
        }
    }

    private void calcOrderBBBH5(Set<BbbH5TradeGoodsDTO.ETO> tradeGoodsDTOSet, BbbH5TradeBuildDTO.DTO tradeGoodsDTO,
                              CommonMarketDTO.MarketSku bestMarketSku, BbbH5TradeGoodsDTO.ETO giftGiveSku,
                              CommonMarketDTO.MarketSku skuCards) {
        log.info("最优营销:{}", JsonUtils.toJson(bestMarketSku));
        log.info("满赠:{}", JsonUtils.toJson(giftGiveSku));
        log.info("优惠券:{}", JsonUtils.toJson(skuCards));
        if (bestMarketSku != null && !bestMarketSku.getSkuPrice().isEmpty()) {
            tradeGoodsDTO.setMarketActiveVO(new CommonMarketVO.ActiveVO().setActiveName(bestMarketSku.getMarketName()));
            //设置sku活动价,并在总价基础上减去活动总价
            BigDecimal cutPriceRate = BigDecimal.ZERO;
            for (BbbH5TradeGoodsDTO.ETO goods : tradeGoodsDTOSet) {
                if (goods.getGoodsId().equals(bestMarketSku.getSpuId())) {
                    BigDecimal skuPrice = bestMarketSku.getSkuPrice().get(goods.getSkuId());
                    if (skuPrice == null) {
                        continue;
                    }
                    goods.setDiscountAmount(goods.getSalePrice().subtract(skuPrice));//优惠金额=售价-活动价
                    goods.setPayAmount(goods.getSalePrice().subtract(goods.getDiscountAmount()));
                    cutPriceRate = cutPriceRate.add(goods.getSalePrice().multiply(new BigDecimal("" + goods.getQuantity())));
                }
            }
            if (bestMarketSku.getMarketName().contains("减")
                    || bestMarketSku.getMarketName().contains("团购")
                    || bestMarketSku.getMarketName().contains("活动")) {
                tradeGoodsDTO.setShopProductAmount(tradeGoodsDTO.getShopProductAmount().subtract(bestMarketSku.getCutPrice()));
                tradeGoodsDTO.getMarketActiveVO().setCutPrice(bestMarketSku.getCutPrice());
            }
            if (bestMarketSku.getMarketName().contains("折")) {
                BigDecimal cutPrice = cutPriceRate.multiply(BigDecimal.TEN.subtract(bestMarketSku.getCutPrice()).divide(BigDecimal.TEN, 2, RoundingMode.HALF_UP));
                tradeGoodsDTO.setShopProductAmount(tradeGoodsDTO.getShopProductAmount().subtract(cutPrice));
                tradeGoodsDTO.getMarketActiveVO().setCutPrice(cutPrice);
            }
        }
        if (skuCards != null) {
            tradeGoodsDTO.setUserCardVO(new CommonMarketVO.UserCardVO().setCardName(skuCards.getMarketName()).setCutPrice(skuCards.getCutPrice()).setCardId(skuCards.getCardId()));
            //合并优惠券与优惠价
            for (BbbH5TradeGoodsDTO.ETO goods : tradeGoodsDTOSet) {
                if (goods.getGoodsId().equals(skuCards.getSpuId())) {
                    BigDecimal skuPrice = skuCards.getSkuPrice().get(goods.getSkuId());
                    if (skuPrice == null) {
                        continue;
                    }
                    //合并优惠券价
                    BigDecimal discountPrice = goods.getSalePrice().subtract(skuPrice).add(goods.getDiscountAmount() != null ? goods.getDiscountAmount() : BigDecimal.ZERO);
                    goods.setDiscountAmount(discountPrice);
                    goods.setPayAmount(goods.getSalePrice().subtract(goods.getDiscountAmount()));
                }
            }
            tradeGoodsDTO.setShopProductAmount(tradeGoodsDTO.getShopProductAmount().subtract(skuCards.getCutPrice()));
        }
        //todo 和总支付价格比较，小于支付价格，则直接加0.01
        for (BbbH5TradeGoodsDTO.ETO goods : tradeGoodsDTOSet) {
            goods.setPayAmount(goods.getPayAmount() != null ? goods.getPayAmount().setScale(2, RoundingMode.HALF_UP) : goods.getSalePrice());
        }
        if (giftGiveSku != null) {
            tradeGoodsDTOSet.add(giftGiveSku);
        }
    }

    @Override
    public void settlementH5BBB(BbbH5TradeSettlementVO.ListVO settlementVO, BbbH5TradeBuildDTO.cartIdsDTO dto) {

        List<CommonMarketDTO.SkuId> skuIds = getIdsBBBH5(settlementVO.getGoodsInfoVOS());
        //1,营销活动与购买产品的对应关系-MarketSku
        //1.1平台活动, 匹配到skuId
        CommonMarketDTO.MarketSku ptSkus =  ptActivityGoodsSkuService.activePtActivitySku(skuIds, dto.getTerminal());
        //1.2团购, 匹配到skuId
        CommonMarketDTO.MarketSku groupSkus = groupbuyGoodsService.activeGroupbuySku(skuIds, dto.getTerminal());
        //1.3满减, 匹配到spuId, 同一spu合并-ok
        CommonMarketDTO.MarketSku cutSkus = cutGoodsService.activeCutPriceSku(skuIds, dto.getTerminal());
        //1.4满折, 匹配到spuId, 同一spu合并-ok
        CommonMarketDTO.MarketSku discountSkus = discountGoodsService.activeDiscountPriceSku(skuIds, dto.getTerminal());
        //1.5各sku活动合并后的最低价, 以上营销互斥 - ok
        CommonMarketDTO.MarketSku margeSkus = marge(skuIds, ptSkus, groupSkus, cutSkus, discountSkus);
        log.info("合并营销活动：{}", JsonUtils.toJson(skuIds));

        //2,购买产品与持有优惠券的对应关系-SkuCard, 同一spu合并 -ok
        CommonMarketDTO.MarketSku skuCards = cardUsersService.activeCardSku(dto.getJwtUserId(), skuIds, dto.getTerminal());

        //1.5满赠, 匹配到spuId, 同一spu合并-ok
        CommonMarketDTO.SkuId giveSkuId = giftGoodsGiveService.activeGiveSku(skuIds, dto.getTerminal());
        BbbH5TradeSettlementVO.ListVO.goodsInfoVO giftGiveSku = giftGoodsGiveService.fillBbcGoodsInfoSettlementVOBBBH5(giveSkuId, dto);

        //3,产品原价及合并后的sku活动价加上sku优惠券,并附上赠品,合并计算
        calcBBBH5(settlementVO, margeSkus, giftGiveSku, skuCards);
    }

    @Override
    public void settlementH5BBB(Set<BbbH5TradeGoodsDTO.ETO> tradeGoodsDTOSet, BbbH5TradeBuildDTO.DTO dto) {
        List<CommonMarketDTO.SkuId> skuIds = getIdsBBBH5(tradeGoodsDTOSet);

        //1,营销活动与购买产品的对应关系-MarketSku
        //1.1平台活动, 匹配到skuId
        CommonMarketDTO.MarketSku ptSkus =  ptActivityGoodsSkuService.activePtActivitySku(skuIds, dto.getTerminal());
        //1.2团购, 匹配到skuId
        CommonMarketDTO.MarketSku groupSkus = groupbuyGoodsService.activeGroupbuySku(skuIds, dto.getTerminal());
        //1.3满减, 匹配到spuId, 同一spu合并-ok
        CommonMarketDTO.MarketSku cutSkus = cutGoodsService.activeCutPriceSku(skuIds, dto.getTerminal());
        //1.4满折, 匹配到spuId, 同一spu合并-ok
        CommonMarketDTO.MarketSku discountSkus = discountGoodsService.activeDiscountPriceSku(skuIds, dto.getTerminal());
        //1.5各sku活动合并后的最低价, 以上营销互斥 - ok
        CommonMarketDTO.MarketSku margeSkus = marge(skuIds, ptSkus, groupSkus, cutSkus, discountSkus);
        log.info("合并营销活动结果后sku信息：{}", JsonUtils.toJson(skuIds));

        //2,购买产品与持有优惠券的对应关系-SkuCard, 同一spu合并 -ok
        CommonMarketDTO.MarketSku skuCards = cardUsersService.activeCardSku(dto.getJwtUserId(), skuIds, dto.getTerminal());

        //1.5满赠, 匹配到spuId, 同一spu合并-ok
        CommonMarketDTO.SkuId giveSkuId = giftGoodsGiveService.activeGiveSku(skuIds, dto.getTerminal());
        BbbH5TradeGoodsDTO.ETO giftGiveSku = giftGoodsGiveService.fillBbcGoodsInfoOrderVOBBBH5(giveSkuId, dto);

        //3,产品原价及合并后的sku活动价加上sku优惠券,并附上赠品,合并计算
        calcOrderBBBH5(tradeGoodsDTOSet, dto, margeSkus, giftGiveSku, skuCards);

    }
}
