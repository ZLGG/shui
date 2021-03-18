package com.gs.lshly.biz.support.trade.service.bbc.impl;

import cn.hutool.core.collection.CollUtil;
import com.gs.lshly.biz.support.trade.service.bbb.pc.IPCBbbMarketMerchantCardUsersService;
import com.gs.lshly.biz.support.trade.service.bbc.IBbcMarketSettleService;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.*;
import com.gs.lshly.common.enums.ActivityTerminalEnum;
import com.gs.lshly.common.enums.TradeTrueFalseEnum;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeBuildDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeGoodsDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeSettlementVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeBuildDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeGoodsDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeSettlementVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeGoodsDTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeSettlementVO;
import com.gs.lshly.common.struct.common.dto.CommonMarketDTO;
import com.gs.lshly.common.struct.common.vo.CommonMarketVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    private List<CommonMarketDTO.SkuId> getIds(List<BbcTradeSettlementVO.ListVO.goodsInfoVO> goodsInfoVOS) {
        List<CommonMarketDTO.SkuId> skuIds = new ArrayList<>();
        for (BbcTradeSettlementVO.ListVO.goodsInfoVO goods : goodsInfoVOS) {
            skuIds.add(new CommonMarketDTO.SkuId().setSkuId(goods.getSkuId()).setSpuId(goods.getGoodsId())
                    .setSkuSalePrice(goods.getSalePrice()).setQuantity(goods.getQuantity()));
        }
        return skuIds;
    }


    private List<CommonMarketDTO.SkuId> getIds(Set<BbcTradeGoodsDTO.ETO> tradeGoodsDTOSet) {
        List<CommonMarketDTO.SkuId> skuIds = new ArrayList<>();
        for (BbcTradeGoodsDTO.ETO goods : tradeGoodsDTOSet) {
            skuIds.add(new CommonMarketDTO.SkuId().setSkuId(goods.getSkuId()).setSpuId(goods.getGoodsId())
                    .setSkuSalePrice(goods.getSalePrice()).setQuantity(goods.getQuantity()));
        }
        return skuIds;
    }

    private List<CommonMarketDTO.SkuId> getIdsBBB(List<BbbTradeSettlementVO.ListVO.goodsInfoVO> goodsInfoVOS) {
        List<CommonMarketDTO.SkuId> skuIds = new ArrayList<>();
        for (BbbTradeSettlementVO.ListVO.goodsInfoVO goods : goodsInfoVOS) {
            skuIds.add(new CommonMarketDTO.SkuId().setSkuId(goods.getSkuId()).setSpuId(goods.getGoodsId())
                    .setSkuSalePrice(goods.getSalePrice()).setQuantity(goods.getQuantity()));
        }
        return skuIds;
    }

    private List<CommonMarketDTO.SkuId> getIdsBBB(Set<BbbTradeGoodsDTO.ETO> tradeGoodsDTOSet) {
        List<CommonMarketDTO.SkuId> skuIds = new ArrayList<>();
        for (BbbTradeGoodsDTO.ETO goods : tradeGoodsDTOSet) {
            skuIds.add(new CommonMarketDTO.SkuId().setSkuId(goods.getSkuId()).setSpuId(goods.getGoodsId())
                    .setSkuSalePrice(goods.getSalePrice()).setQuantity(goods.getQuantity()));
        }
        return skuIds;
    }

    private List<CommonMarketDTO.SkuId> getIdsBBBH5(List<BbbH5TradeSettlementVO.ListVO.goodsInfoVO> goodsInfoVOS) {
        List<CommonMarketDTO.SkuId> skuIds = new ArrayList<>();
        for (BbbH5TradeSettlementVO.ListVO.goodsInfoVO goods : goodsInfoVOS) {
            skuIds.add(new CommonMarketDTO.SkuId().setSkuId(goods.getSkuId()).setSpuId(goods.getGoodsId())
                    .setSkuSalePrice(goods.getSalePrice()).setQuantity(goods.getQuantity()));
        }
        return skuIds;
    }

    private List<CommonMarketDTO.SkuId> getIdsBBBH5(Set<BbbH5TradeGoodsDTO.ETO> tradeGoodsDTOSet) {
        List<CommonMarketDTO.SkuId> skuIds = new ArrayList<>();
        for (BbbH5TradeGoodsDTO.ETO goods : tradeGoodsDTOSet) {
            skuIds.add(new CommonMarketDTO.SkuId().setSkuId(goods.getSkuId()).setSpuId(goods.getGoodsId())
                    .setSkuSalePrice(goods.getSalePrice()).setQuantity(goods.getQuantity()));
        }
        return skuIds;
    }

    private CommonMarketDTO.MarketSku marge(List<CommonMarketDTO.SkuId> goods, CommonMarketDTO.MarketSku ptSkus, CommonMarketDTO.MarketSku groupSkus,
                                            CommonMarketDTO.MarketSku cutSkus, CommonMarketDTO.MarketSku discountSkus) {
        log.info("平台sku活动:{}", ptSkus);
        log.info("团购sku活动:{}", groupSkus);
        log.info("满减sku活动:{}", cutSkus);
        log.info("满折sku活动:{}", discountSkus);

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

        return CommonMarketDTO.SkuId.calcBestMarketSku(marketSkus, goods);
    }

    private void calc(BbcTradeSettlementVO.ListVO settlementVO, CommonMarketDTO.MarketSku bestMarketSku,
                      BbcTradeSettlementVO.ListVO.goodsInfoVO giftGiveSku, CommonMarketDTO.MarketSku skuCards) {
        log.info("合并后的sku营销方案:{}", bestMarketSku);
        log.info("满赠sku:{}", giftGiveSku);
        log.info("sku优惠券方案:{}", skuCards);
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
            if (bestMarketSku.getMarketName().contains("减")) {
                settlementVO.setGoodsAmount(settlementVO.getGoodsAmount().subtract(bestMarketSku.getCutPrice()));
            }
            if (bestMarketSku.getMarketName().contains("折")) {
                BigDecimal cutPrice = cutPriceRate.multiply(BigDecimal.TEN.subtract(bestMarketSku.getCutPrice()).divide(BigDecimal.TEN, 2, RoundingMode.HALF_UP));
                settlementVO.setGoodsAmount(settlementVO.getGoodsAmount().subtract(cutPrice));
            }
        }
        if (skuCards != null) {
            settlementVO.setUserCardVO(new CommonMarketVO.UserCardVO().setCardName(skuCards.getMarketName()));
            //合并优惠券与优惠价
            for (BbcTradeSettlementVO.ListVO.goodsInfoVO goods : settlementVO.getGoodsInfoVOS()) {
                if (goods.getGoodsId().equals(skuCards.getSpuId())) {
                    BigDecimal skuPrice = skuCards.getSkuPrice().get(goods.getSkuId());
                    if (skuPrice == null) {
                        continue;
                    }
                    //合并优惠券价
                    skuPrice = goods.getActivePrice() == null ? skuPrice : goods.getActivePrice().subtract(goods.getSalePrice().subtract(skuPrice));
                    goods.setActivePrice(skuPrice);
                }
            }
            settlementVO.setGoodsAmount(settlementVO.getGoodsAmount().subtract(skuCards.getCutPrice()));
        }

        if (giftGiveSku != null) {
            giftGiveSku.setGoodsId("gift").setSkuId("gift");
            settlementVO.getGoodsInfoVOS().add(giftGiveSku);
        }
    }


    private void calcOrder(Set<BbcTradeGoodsDTO.ETO> tradeGoodsDTOSet, BbcTradeBuildDTO.DTO tradeGoodsDTO, CommonMarketDTO.MarketSku bestMarketSku,
                           BbcTradeGoodsDTO.ETO giftGiveSku, CommonMarketDTO.MarketSku skuCards) {
        log.info("合并后的sku营销方案:{}", bestMarketSku);
        log.info("满赠sku:{}", giftGiveSku);
        log.info("sku优惠券方案:{}", skuCards);
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
                    goods.setDiscountAmount(skuPrice);
                    cutPriceRate = cutPriceRate.add(goods.getSalePrice().multiply(new BigDecimal("" + goods.getQuantity())));
                }
            }
            if (bestMarketSku.getMarketName().contains("减")) {
                tradeGoodsDTO.setShopProductAmount(tradeGoodsDTO.getShopProductAmount().subtract(bestMarketSku.getCutPrice()));
            }
            if (bestMarketSku.getMarketName().contains("折")) {
                BigDecimal cutPrice = cutPriceRate.multiply(BigDecimal.TEN.subtract(bestMarketSku.getCutPrice()).divide(BigDecimal.TEN, 2, RoundingMode.HALF_UP));
                tradeGoodsDTO.setShopProductAmount(tradeGoodsDTO.getShopProductAmount().subtract(cutPrice));
            }
        }
        if (skuCards != null) {
            tradeGoodsDTO.setUserCardVO(new CommonMarketVO.UserCardVO().setCardName(skuCards.getMarketName()));
            //合并优惠券与优惠价
            for (BbcTradeGoodsDTO.ETO goods : tradeGoodsDTOSet) {
                if (goods.getGoodsId().equals(skuCards.getSpuId())) {
                    BigDecimal skuPrice = skuCards.getSkuPrice().get(goods.getSkuId());
                    if (skuPrice == null) {
                        continue;
                    }
                    //合并优惠券价
                    skuPrice = goods.getDiscountAmount() == null ? skuPrice : goods.getDiscountAmount().subtract(goods.getSalePrice().subtract(skuPrice));
                    goods.setDiscountAmount(skuPrice);
                }
            }
            tradeGoodsDTO.setShopProductAmount(tradeGoodsDTO.getShopProductAmount().subtract(skuCards.getCutPrice()));
        }

        if (giftGiveSku != null) {
            tradeGoodsDTOSet.add(giftGiveSku);
        }
    }

    @Override
    public void settlement(BbcTradeSettlementVO.ListVO settlementVO, BbcTradeBuildDTO.cartIdsDTO dto) {

        List<CommonMarketDTO.SkuId> skuIds = getIds(settlementVO.getGoodsInfoVOS());
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

        //2,购买产品与持有优惠券的对应关系-SkuCard, 同一spu合并 -ok
        CommonMarketDTO.MarketSku skuCards = cardUsersService.activeCardSku(dto.getJwtUserId(), skuIds, dto.getTerminal());

        //1.5满赠, 匹配到spuId, 同一spu合并-ok
        CommonMarketDTO.SkuId giveSkuId = giftGoodsGiveService.activeGiveSku(skuIds, dto.getTerminal());
        BbcTradeSettlementVO.ListVO.goodsInfoVO giftGiveSku = giftGoodsGiveService.fillBbcGoodsInfoSettlementVO(giveSkuId);

        //3,产品原价及合并后的sku活动价加上sku优惠券,并附上赠品,合并计算
        calc(settlementVO, margeSkus, giftGiveSku, skuCards);

    }

    @Override
    public void settlement(Set<BbcTradeGoodsDTO.ETO> tradeGoodsDTOSet, BbcTradeBuildDTO.DTO dto) {

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

        //2,购买产品与持有优惠券的对应关系-SkuCard, 同一spu合并 -ok
        CommonMarketDTO.MarketSku skuCards = cardUsersService.activeCardSku(dto.getJwtUserId(), skuIds, dto.getTerminal());

        //1.5满赠, 匹配到spuId, 同一spu合并-ok  TODO
        CommonMarketDTO.SkuId giveSkuId = giftGoodsGiveService.activeGiveSku(skuIds, dto.getTerminal());
        BbcTradeGoodsDTO.ETO giftGiveSku = giftGoodsGiveService.fillBbcGoodsInfoOrderVO(giveSkuId, dto);

        //3,产品原价及合并后的sku活动价加上sku优惠券,并附上赠品,合并计算
        calcOrder(tradeGoodsDTOSet, dto, margeSkus, giftGiveSku, skuCards);
    }


    private void calcBBB(BbbTradeSettlementVO.ListVO settlementVO, CommonMarketDTO.MarketSku bestMarketSku,
                      BbbTradeSettlementVO.ListVO.goodsInfoVO giftGiveSku, CommonMarketDTO.MarketSku skuCards) {
        log.info("合并后的sku营销方案:{}", bestMarketSku);
        log.info("满赠sku:{}", giftGiveSku);
        log.info("sku优惠券方案:{}", skuCards);
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
            if (bestMarketSku.getMarketName().contains("减")) {
                settlementVO.setGoodsAmount(settlementVO.getGoodsAmount().subtract(bestMarketSku.getCutPrice()));
            }
            if (bestMarketSku.getMarketName().contains("折")) {
                BigDecimal cutPrice = cutPriceRate.multiply(BigDecimal.TEN.subtract(bestMarketSku.getCutPrice()).divide(BigDecimal.TEN, 2, RoundingMode.HALF_UP));
                settlementVO.setGoodsAmount(settlementVO.getGoodsAmount().subtract(cutPrice));
            }
        }
        if (skuCards != null) {
            settlementVO.setUserCardVO(new CommonMarketVO.UserCardVO().setCardName(skuCards.getMarketName()));
            //合并优惠券与优惠价
            for (BbbTradeSettlementVO.ListVO.goodsInfoVO goods : settlementVO.getGoodsInfoVOS()) {
                if (goods.getGoodsId().equals(skuCards.getSpuId())) {
                    BigDecimal skuPrice = skuCards.getSkuPrice().get(goods.getSkuId());
                    if (skuPrice == null) {
                        continue;
                    }
                    //合并优惠券价
                    skuPrice = goods.getActivePrice() == null ? skuPrice : goods.getActivePrice().subtract(goods.getSalePrice().subtract(skuPrice));
                    goods.setActivePrice(skuPrice);
                }
            }
            settlementVO.setGoodsAmount(settlementVO.getGoodsAmount().subtract(skuCards.getCutPrice()));
        }

        if (giftGiveSku != null) {
            giftGiveSku.setGoodsId("gift").setSkuId("gift");
            settlementVO.getGoodsInfoVOS().add(giftGiveSku);
        }
    }

    private void calcOrderBBB(Set<BbbTradeGoodsDTO.ETO> tradeGoodsDTOSet, BbbTradeBuildDTO.DTO tradeGoodsDTO,
                              CommonMarketDTO.MarketSku bestMarketSku, BbbTradeGoodsDTO.ETO giftGiveSku,
                              CommonMarketDTO.MarketSku skuCards) {
        log.info("合并后的sku营销方案:{}", bestMarketSku);
        log.info("满赠sku:{}", giftGiveSku);
        log.info("sku优惠券方案:{}", skuCards);
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
                    goods.setDiscountAmount(skuPrice);
                    cutPriceRate = cutPriceRate.add(goods.getSalePrice().multiply(new BigDecimal("" + goods.getQuantity())));
                }
            }
            if (bestMarketSku.getMarketName().contains("减")) {
                tradeGoodsDTO.setShopProductAmount(tradeGoodsDTO.getShopProductAmount().subtract(bestMarketSku.getCutPrice()));
            }
            if (bestMarketSku.getMarketName().contains("折")) {
                BigDecimal cutPrice = cutPriceRate.multiply(BigDecimal.TEN.subtract(bestMarketSku.getCutPrice()).divide(BigDecimal.TEN, 2, RoundingMode.HALF_UP));
                tradeGoodsDTO.setShopProductAmount(tradeGoodsDTO.getShopProductAmount().subtract(cutPrice));
            }
        }
        if (skuCards != null) {
            tradeGoodsDTO.setUserCardVO(new CommonMarketVO.UserCardVO().setCardName(skuCards.getMarketName()));
            //合并优惠券与优惠价
            for (BbbTradeGoodsDTO.ETO goods : tradeGoodsDTOSet) {
                if (goods.getGoodsId().equals(skuCards.getSpuId())) {
                    BigDecimal skuPrice = skuCards.getSkuPrice().get(goods.getSkuId());
                    if (skuPrice == null) {
                        continue;
                    }
                    //合并优惠券价
                    skuPrice = goods.getDiscountAmount() == null ? skuPrice : goods.getDiscountAmount().subtract(goods.getSalePrice().subtract(skuPrice));
                    goods.setDiscountAmount(skuPrice);
                }
            }
            tradeGoodsDTO.setShopProductAmount(tradeGoodsDTO.getShopProductAmount().subtract(skuCards.getCutPrice()));
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
        log.info("合并后的sku营销方案:{}", bestMarketSku);
        log.info("满赠sku:{}", giftGiveSku);
        log.info("sku优惠券方案:{}", skuCards);
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
            if (bestMarketSku.getMarketName().contains("减")) {
                settlementVO.setGoodsAmount(settlementVO.getGoodsAmount().subtract(bestMarketSku.getCutPrice()));
            }
            if (bestMarketSku.getMarketName().contains("折")) {
                BigDecimal cutPrice = cutPriceRate.multiply(BigDecimal.TEN.subtract(bestMarketSku.getCutPrice()).divide(BigDecimal.TEN, 2, RoundingMode.HALF_UP));
                settlementVO.setGoodsAmount(settlementVO.getGoodsAmount().subtract(cutPrice));
            }
        }
        if (skuCards != null) {
            settlementVO.setUserCardVO(new CommonMarketVO.UserCardVO().setCardName(skuCards.getMarketName()));
            //合并优惠券与优惠价
            for (BbbH5TradeSettlementVO.ListVO.goodsInfoVO goods : settlementVO.getGoodsInfoVOS()) {
                if (goods.getGoodsId().equals(skuCards.getSpuId())) {
                    BigDecimal skuPrice = skuCards.getSkuPrice().get(goods.getSkuId());
                    if (skuPrice == null) {
                        continue;
                    }
                    //合并优惠券价
                    skuPrice = goods.getActivePrice() == null ? skuPrice : goods.getActivePrice().subtract(goods.getSalePrice().subtract(skuPrice));
                    goods.setActivePrice(skuPrice);
                }
            }
            settlementVO.setGoodsAmount(settlementVO.getGoodsAmount().subtract(skuCards.getCutPrice()));
        }

        if (giftGiveSku != null) {
            giftGiveSku.setGoodsId("gift").setSkuId("gift");
            settlementVO.getGoodsInfoVOS().add(giftGiveSku);
        }
    }

    private void calcOrderBBBH5(Set<BbbH5TradeGoodsDTO.ETO> tradeGoodsDTOSet, BbbH5TradeBuildDTO.DTO tradeGoodsDTO,
                              CommonMarketDTO.MarketSku bestMarketSku, BbbH5TradeGoodsDTO.ETO giftGiveSku,
                              CommonMarketDTO.MarketSku skuCards) {
        log.info("合并后的sku营销方案:{}", bestMarketSku);
        log.info("满赠sku:{}", giftGiveSku);
        log.info("sku优惠券方案:{}", skuCards);
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
                    goods.setDiscountAmount(skuPrice);
                    cutPriceRate = cutPriceRate.add(goods.getSalePrice().multiply(new BigDecimal("" + goods.getQuantity())));
                }
            }
            if (bestMarketSku.getMarketName().contains("减")) {
                tradeGoodsDTO.setShopProductAmount(tradeGoodsDTO.getShopProductAmount().subtract(bestMarketSku.getCutPrice()));
            }
            if (bestMarketSku.getMarketName().contains("折")) {
                BigDecimal cutPrice = cutPriceRate.multiply(BigDecimal.TEN.subtract(bestMarketSku.getCutPrice()).divide(BigDecimal.TEN, 2, RoundingMode.HALF_UP));
                tradeGoodsDTO.setShopProductAmount(tradeGoodsDTO.getShopProductAmount().subtract(cutPrice));
            }
        }
        if (skuCards != null) {
            tradeGoodsDTO.setUserCardVO(new CommonMarketVO.UserCardVO().setCardName(skuCards.getMarketName()));
            //合并优惠券与优惠价
            for (BbbH5TradeGoodsDTO.ETO goods : tradeGoodsDTOSet) {
                if (goods.getGoodsId().equals(skuCards.getSpuId())) {
                    BigDecimal skuPrice = skuCards.getSkuPrice().get(goods.getSkuId());
                    if (skuPrice == null) {
                        continue;
                    }
                    //合并优惠券价
                    skuPrice = goods.getDiscountAmount() == null ? skuPrice : goods.getDiscountAmount().subtract(goods.getSalePrice().subtract(skuPrice));
                    goods.setDiscountAmount(skuPrice);
                }
            }
            tradeGoodsDTO.setShopProductAmount(tradeGoodsDTO.getShopProductAmount().subtract(skuCards.getCutPrice()));
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

        //2,购买产品与持有优惠券的对应关系-SkuCard, 同一spu合并 -ok
        CommonMarketDTO.MarketSku skuCards = cardUsersService.activeCardSku(dto.getJwtUserId(), skuIds, dto.getTerminal());

        //1.5满赠, 匹配到spuId, 同一spu合并-ok
        CommonMarketDTO.SkuId giveSkuId = giftGoodsGiveService.activeGiveSku(skuIds, dto.getTerminal());
        BbbH5TradeGoodsDTO.ETO giftGiveSku = giftGoodsGiveService.fillBbcGoodsInfoOrderVOBBBH5(giveSkuId, dto);

        //3,产品原价及合并后的sku活动价加上sku优惠券,并附上赠品,合并计算
        calcOrderBBBH5(tradeGoodsDTOSet, dto, margeSkus, giftGiveSku, skuCards);

    }
}
