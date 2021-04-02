package com.gs.lshly.common.struct.common.dto;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.utils.JsonUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * 营销活动DTO
 * @author lxus
 * @since 2021-1-28
 */
@Slf4j
public class CommonMarketDTO implements Serializable {

    @Data
    @Accessors(chain = true)
    @ToString
    public static class MarketSku extends BaseDTO {

        @ApiModelProperty(value = "优惠券id")
        private String cardId;

        private String skuTmpId;

        private BigDecimal skuTmpPrice;

        @ApiModelProperty(value = "skuId-skuPrice-map")
        private Map<String, BigDecimal> skuPrice = new HashMap<>();

        @ApiModelProperty(value = "spuId")
        private String spuId;

        @ApiModelProperty(value = "营销活动类型/名称")
        private String marketName;

        @ApiModelProperty("满多少")
        private BigDecimal toPrice;


        private void init() {
            toPrice = BigDecimal.ZERO;
            skuPrice.put(skuTmpId, skuTmpPrice);
        }

        public static MarketSku pickBest(List<MarketSku> source, List<SkuId> skuIds) {
            if (source == null || source.isEmpty()) {
                return null;
            }
            BigDecimal tmpPrice = BigDecimal.ZERO;
            MarketSku best = null;
            for(MarketSku src : source){
                src.init();
                for (SkuId sku : skuIds) {
                    if (sku.getSkuId().equals(src.getSkuTmpId())) {
                        //原价,平台活动价
                        log.info("sku:{}:原价{},平台活动价{}",sku.getSkuId(), sku.getSkuSalePrice(), src.getSkuTmpPrice());
                        BigDecimal activePrice = sku.getSkuSalePrice().subtract(src.getSkuTmpPrice()).multiply(new BigDecimal("" + sku.getQuantity()));
                        if (activePrice.compareTo(tmpPrice) > 0) {
                            log.info("优于原价,总优惠：" + activePrice);
                            best = src;
                            tmpPrice = activePrice;
                            best.setCutPrice(activePrice);
                        }
                    }
                }
            }
            return best;
        }

        @Override
        public String toString() {
            return "MarketSku{" +
                    "marketName='" + marketName + '\'' +
                    ", 满=" + toPrice +
                    ", 减/折=" + cutPrice +
                    '}';
        }

        @ApiModelProperty("减多少")
        private BigDecimal cutPrice;

        public static MarketSku calcMarketSkuPrice(String fullType, String type, Map.Entry<String, List<SkuId>> spuEntry, BigDecimal totalSpuPrice,
                                                   ToCutPrice cutPrice ){

            CommonMarketDTO.MarketSku marketSku = new CommonMarketDTO.MarketSku()
                    .setSpuId(spuEntry.getKey()).setCardId(cutPrice.getCardId())
                    .setMarketName(fullType + cutPrice.getToPrice() + type + cutPrice.getCutPrice())
                    .setToPrice(cutPrice.getToPrice()).setCutPrice(cutPrice.getCutPrice());
            //将优惠价格打散到spu商品分组内的各商品中.
            for(SkuId good : spuEntry.getValue()){
                log.info("skuId:"+ JsonUtils.toJson(good));
                //sku售价 * sku 数量 / spu组总额 = 百分比 * 匹配优惠金额 = sku所能优惠的金额 / sku数量 = sku优惠价格(存在小数点误差)
                BigDecimal skuCutPrice = BigDecimal.ZERO;
                if("折".equals(type)) {
                    //折扣
                    skuCutPrice = good.getSkuActivePrice().multiply(BigDecimal.TEN.subtract(cutPrice.getCutPrice())).divide(BigDecimal.TEN, 3, RoundingMode.HALF_UP);
                    log.info("打折后单价-取3位小数:" + good.getSkuActivePrice() + " * (" + BigDecimal.TEN + "-" + (cutPrice.getCutPrice()) + ") / " + (BigDecimal.TEN) + " = " + skuCutPrice);
                } else {
                    //直接扣减
                    skuCutPrice = good.getSkuActivePrice().multiply(new BigDecimal(good.getQuantity() + ""))
                            .divide(totalSpuPrice, 3, RoundingMode.HALF_UP)
                            .multiply(cutPrice.getCutPrice())
                            .divide(new BigDecimal(good.getQuantity() + ""), 3, RoundingMode.HALF_UP);

                    log.info("扣减后单价-取3位小数:" + good.getSkuActivePrice()+" * "+good.getQuantity()
                            + " / " + totalSpuPrice + " * " + cutPrice.getCutPrice() + " / " + good.getQuantity() + " = " + skuCutPrice);

                }

                BigDecimal skuPrice = good.getSkuSalePrice().subtract(skuCutPrice);
                log.info("skuId[" + good.getSkuId() + "]spuId[" + spuEntry.getKey() + "]使用优惠价格为:" + skuPrice);
                marketSku.getSkuPrice().put(good.getSkuId(), skuPrice);
            }
            return marketSku;
        }

    }

    @Data
    @Accessors(chain = true)
    public static class SkuCard extends BaseDTO {

        private String cardId;

        @ApiModelProperty(value = "skuId")
        private String skuId;

        @ApiModelProperty(value = "spuId")
        private String spuId;

        @ApiModelProperty(value = "优惠券类型/名称")
        private String cardName;

        @ApiModelProperty("满多少")
        private BigDecimal toPrice;

        @ApiModelProperty("减多少")
        private BigDecimal cutPrice;

        @Override
        public String toString() {
            return "SkuCard{" +
                    "cardName='" + cardName + '\'' +
                    ", 满=" + toPrice +
                    ", 减=" + cutPrice +
                    '}';
        }

        public static Map<String, List<SkuCard>> initAllRuleToMap(List<SkuCard> skuCards) {
            Map<String, List<SkuCard>> allSkuCuts = new HashMap<>();
            for (SkuCard card : skuCards) {
                String spuId = card.getSpuId();
                //按spu分组满减规则
                List<SkuCard> cards = allSkuCuts.get(spuId);
                if (cards == null) {
                    cards = new ArrayList<>();
                    allSkuCuts.put(spuId, cards);
                }
                if (card.getCutPrice().compareTo(BigDecimal.ZERO) <= 0) {
                    log.error("优惠券配置错误，减额小于0");
                }
                if (card.getToPrice().compareTo(BigDecimal.ZERO) <= 0) {
                    log.error("优惠券配置错误，满额小于0");
                }
                cards.add(new SkuCard().setCardId(card.getCardId()).setSpuId(spuId).setCardName(card.getCardName())
                        .setCutPrice(card.getCutPrice().setScale(2, BigDecimal.ROUND_HALF_DOWN))
                        .setToPrice(card.getToPrice().setScale(2, BigDecimal.ROUND_HALF_DOWN)));

            }
            return allSkuCuts;
        }

        public static ToCutPrice match(List<SkuCard> prices, BigDecimal totalPrice) {
            for (SkuCard price : prices) {
                if (totalPrice.compareTo(price.getToPrice()) >= 0) {
                    return new ToCutPrice().setCardId(price.getCardId()).setToPrice(price.getToPrice()).setCutPrice(price.getCutPrice());
                }
            }
            return null;
        }
    }

    @Data
    @Accessors(chain = true)
    public static class SkuId extends BaseDTO {

        @ApiModelProperty(value = "skuId")
        private String skuId;

        @ApiModelProperty(value = "spuId")
        private String spuId;

        @ApiModelProperty(value = "skuSalePrice")
        private BigDecimal skuSalePrice;

        @ApiModelProperty(value = "quantity")
        private Integer quantity;

        @ApiModelProperty(value = "skuActivePrice")
        private BigDecimal skuActivePrice;



        public static Map<String, List<SkuId>> goodsGroupBySpuId(List<SkuId> list) {
            Map<String, List<SkuId>> allSpu = new HashMap<>();
            for (SkuId good : list) {
                String spuId = good.getSpuId();
                List<SkuId> spuList = allSpu.get(spuId);
                if (spuList == null) {
                    spuList = new ArrayList<>();
                    allSpu.put(spuId, spuList);
                }
                spuList.add(good);
            }
            return allSpu;
        }

        public static BigDecimal calcTotalSalePrice(List<SkuId> list) {
            BigDecimal totalSpuPrice = BigDecimal.ZERO;
            for (SkuId good : list) {
                totalSpuPrice = totalSpuPrice.add(good.getSkuActivePrice().multiply(new BigDecimal(good.getQuantity() + "")));
            }
            return totalSpuPrice;
        }


        public static CommonMarketDTO.MarketSku calcBestMarketSku(List<CommonMarketDTO.MarketSku> marketSkus, List<SkuId> goods, boolean updateSkuIdActivePrice) {

            BigDecimal bestTotalPrice = null;
            CommonMarketDTO.MarketSku bestMarkSku = null;

            for(CommonMarketDTO.MarketSku marketSku : marketSkus) {

                if (marketSku.getSkuPrice() == null || marketSku.getSkuPrice().isEmpty()) {
                    continue;
                }
                BigDecimal goodsTotalPrice = BigDecimal.ZERO;
                for (SkuId good : goods) {
                    BigDecimal skuPrice = marketSku.getSkuPrice().get(good.getSkuId());
                    skuPrice = skuPrice != null ? skuPrice : good.getSkuSalePrice();

                    goodsTotalPrice = goodsTotalPrice.add(skuPrice.multiply(new BigDecimal(good.getQuantity() + "")));

                }

                if (bestTotalPrice == null || bestTotalPrice.compareTo(goodsTotalPrice) > 0) {
                    bestMarkSku = marketSku;
                    bestTotalPrice = goodsTotalPrice;
                    if (updateSkuIdActivePrice) {
                        for (SkuId good : goods) {
                            good.setSkuActivePrice(bestMarkSku.getSkuPrice() != null && bestMarkSku.getSkuPrice().get(good.getSkuId()) != null ?
                                    bestMarkSku.getSkuPrice().get(good.getSkuId()) : good.getSkuSalePrice());
                        }
                    }
                }
            }

            return bestMarkSku;
        }
    }


    @Data
    @Accessors(chain = true)
    @ApiModel("满减")
    public static class SkuCutRule extends BaseDTO {

        @ApiModelProperty(value = "skuId")
        private String skuId;

        @ApiModelProperty(value = "spuId")
        private String spuId;

        @ApiModelProperty(value = "满减规则")
        private List<ToCutPrice> toCutPrices;

        @ApiModelProperty(value = "规则定义", hidden = true)
        private String defRuleStr;

        public static List<ToCutPrice> allCutPrice(List<SkuCutRule> list) {
            if (list == null || list.isEmpty()) {
                return null;
            }
            List<ToCutPrice> prices = new ArrayList<>();
            for (CommonMarketDTO.SkuCutRule rule : list) {
                prices.addAll(rule.getToCutPrices());
            }
            return prices;
        }

        public static Map<String, List<SkuCutRule>> initAllRuleToMap(List<SkuCutRule> cuts) {
            Map<String, List<SkuCutRule>> allSkuCuts = new HashMap<>();
            for (SkuCutRule cut : cuts) {
                String spuId = cut.getSpuId();
                //按spu分组满减规则
                List<SkuCutRule> goodsCut = allSkuCuts.get(spuId);
                if (goodsCut == null) {
                    goodsCut = new ArrayList<>();
                    allSkuCuts.put(spuId, goodsCut);
                }
                goodsCut.add(new SkuCutRule().setSpuId(spuId).setDefRuleStr(cut.getDefRuleStr()).parseRule());

            }
            return allSkuCuts;
        }

        public SkuCutRule parseRule() {
            toCutPrices = parse(spuId, defRuleStr, "满减");
            return this;
        }
        // 满减 - 先按 cut_price 降序, 再按 to_price 升序 需2次排序
        public static void sort(List<ToCutPrice> toCutPrices){
            toCutPrices.sort((o1, o2) -> o1.cutPrice.compareTo(o2.cutPrice) > 0 ? -1 : 1);
            toCutPrices.sort((o1, o2) -> (o1.cutPrice.compareTo(o2.cutPrice)==0 && o2.toPrice.compareTo(o1.toPrice) > 0) ? -1 : 1);
        }
    }

    private static List<ToCutPrice> parse(String spuId, String defRuleStr, String ruleTypeName){
        //parse json content
        if (StrUtil.isBlank(defRuleStr)) {
            log.error("spu[" + spuId + "]-["+ruleTypeName+"]-规则错误:[定义为空]");
        }
        JSONArray array = null;
        List<ToCutPrice> list = new ArrayList<>();
        try {
            array = JSONUtil.parseArray(defRuleStr);
        } catch (Exception e) {
            log.error("spu[" + spuId + "]-["+ruleTypeName+"]-规则错误:" + defRuleStr + "解析json失败");
            return list;
        }
        if (array == null || array.size() == 0) {
            log.error("spu[" + spuId + "]-["+ruleTypeName+"]-规则错误:" + defRuleStr + "解析json无内容");
            return list;
        }
        //valid content
        ListIterator<Object> objects = array.listIterator();
        while (objects.hasNext()) {
            JSONObject json = (JSONObject) objects.next();
            BigDecimal toPrice = json.getBigDecimal("to_price");
            if (toPrice == null) {
                log.error("spu[" + spuId + "]-["+ruleTypeName+"]-规则错误:" + defRuleStr + "存在未设置'to_price'值的错误规则");
                continue;
            }
            BigDecimal cutPrice = json.getBigDecimal("cut_price");
            if (cutPrice == null) {
                log.error("spu[" + spuId + "]-["+ruleTypeName+"]-规则错误:" + defRuleStr + "存在未设置'to_price'值的错误规则");
                continue;
            }
            //price valid
            if (cutPrice.compareTo(BigDecimal.ZERO) <= 0 || toPrice.compareTo(BigDecimal.ZERO) <= 0) {
                log.error("spu[" + spuId + "]-[" + ruleTypeName + "]-规则错误:" + defRuleStr + "存在 'cut_price'或'to_price' 小于等于0 的错误规则");
                continue;
            }
            if (ruleTypeName.equals("满减") && cutPrice.compareTo(toPrice) >= 0) {
                log.error("spu[" + spuId + "]-[" + ruleTypeName + "]-规则错误:" + defRuleStr + "存在 'cut_price'>='to_price' 的错误规则");
                continue;
            }
            if (ruleTypeName.equals("满折") && cutPrice.compareTo(BigDecimal.TEN) >= 0) {
                log.error("spu[" + spuId + "]-[" + ruleTypeName + "]-规则错误:" + defRuleStr + "存在 'cut_price'>=10 的错误规则");
                continue;
            }
            list.add(new ToCutPrice().setToPrice(toPrice).setCutPrice(cutPrice));
        }
        return list;
    }


    public static void main(String[] args) {
//        CommonMarketDTO.parse("aanull", null, "满减");
//        CommonMarketDTO.parse("aaempty", "", "满减");
//        CommonMarketDTO.parse("aaempty", "{adfadsf}", "满减");
//        CommonMarketDTO.parse("aa0", "[]", "满减");
//        CommonMarketDTO.parse("aa0", "[{\"to_price\":\"10\",\"cut_price\":\"1\"}]", "满减");
//        CommonMarketDTO.parse("aa0", "[{\"to_price\":\"10\",\"cut_price\":\"0\"}]", "满减");
//        CommonMarketDTO.parse("aa0", "[{\"to_price\":\"10\",\"cut_price\":\"-10\"}]", "满减");
//        CommonMarketDTO.parse("aa0", "[{\"to_price\":\"10\",\"cut_price\":\"20\"}]", "满减");
//        CommonMarketDTO.parse("aa0", "[{\"to_price\":\"10\",\"cut_price\":\"5\"}," +
//                "{\"to_price\":\"20\",\"cut_price\":\"11\"}," +
//                "{\"to_price\":\"20\",\"cut_price\":\"12\"}," +
//                "{\"to_price\":\"18\",\"cut_price\":\"12\"}," +
//                "{\"to_price\":\"5\",\"cut_price\":\"1\"}," +
//                "{\"to_price\":\"20\",\"cut_price\":\"10\"}," +
//                "{\"to_price\":\"15\",\"cut_price\":\"13\"}]", "满减");

//        CommonMarketDTO.parse("aa0", "[{\"to_price\":\"10\",\"cut_price\":\"20\"}]", "满折");

//        CommonMarketDTO.parse("aa0", "[{\"to_price\":\"100\",\"cut_price\":\"1\"}," +
//                "{\"to_price\":\"100\",\"cut_price\":\"3\"}," +
//                "{\"to_price\":\"80\",\"cut_price\":\"6\"}," +
//                "{\"to_price\":\"30\",\"cut_price\":\"7\"}," +
//                "{\"to_price\":\"90\",\"cut_price\":\"1\"},{\"to_price\":\"10\",\"cut_price\":\"1\"}]", "满折");
    }

    @Data
    @Accessors(chain = true)
    public static class ToCutPrice implements Serializable {

        private String cardId;

        @ApiModelProperty(value = "触发金额")
        private BigDecimal toPrice;

        @ApiModelProperty(value = "减金额")
        private BigDecimal cutPrice;

        public static ToCutPrice match(List<ToCutPrice> prices, BigDecimal totalPrice) {
            for (ToCutPrice price : prices) {
                if (totalPrice.compareTo(price.getToPrice()) >= 0) {
                    return price;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return "ToCutPrice{" +
                    "满=" + toPrice +
                    ", 减/折=" + cutPrice +
                    '}';
        }
    }


    @Data
    @Accessors(chain = true)
    @ApiModel("满折")
    public static class SkuDiscountRule extends BaseDTO {

        @ApiModelProperty(value = "skuId")
        private String skuId;

        @ApiModelProperty(value = "spuId")
        private String spuId;

        @ApiModelProperty(value = "满折规则")
        private List<ToCutPrice> toCutPrices;

        @ApiModelProperty(value = "规则定义", hidden = true)
        private String defRuleStr;

        public static Map<String, List<SkuDiscountRule>> initAllRuleToMap(List<SkuDiscountRule> discountRules) {
            Map<String, List<SkuDiscountRule>> allDiscountRuleMap = new HashMap<>();
            for (SkuDiscountRule cut : discountRules) {
                String spuId = cut.getSpuId();
                //按spu分组满减规则
                List<SkuDiscountRule> goodsDiscount = allDiscountRuleMap.get(spuId);
                if (goodsDiscount == null) {
                    goodsDiscount = new ArrayList<>();
                    allDiscountRuleMap.put(spuId, goodsDiscount);
                }
                goodsDiscount.add(new SkuDiscountRule().setSpuId(spuId).setDefRuleStr(cut.getDefRuleStr()).parseRule());

            }
            return allDiscountRuleMap;
        }

        public static List<ToCutPrice> allCutPrice(List<SkuDiscountRule> list){
            if (list == null || list.isEmpty()) {
                return null;
            }
            List<ToCutPrice> prices = new ArrayList<>();
            for (CommonMarketDTO.SkuDiscountRule rule : list) {
                prices.addAll(rule.getToCutPrices());
            }
            return prices;
        }

        public SkuDiscountRule parseRule() {
            toCutPrices = parse(spuId, defRuleStr, "满折");
            return this;
        }

        // 瞒折 - 先按 cut_price 升序, 再按 to_price 升序 需2次排序
        public static void sort(List<ToCutPrice> toCutPrices){
            toCutPrices.sort((o1, o2) -> o1.cutPrice.compareTo(o2.cutPrice) > 0 ? 1 : -1);
            toCutPrices.sort((o1, o2) -> o1.cutPrice.compareTo(o2.cutPrice)==0 && o2.toPrice.compareTo(o1.toPrice) > 0 ? -1 : 1);
        }
    }

    @Data
    @Accessors(chain = true)
    @ApiModel("满赠")
    @ToString
    public static class SkuGive extends BaseDTO {

        @ApiModelProperty(value = "skuId")
        private String skuId;

        @ApiModelProperty(value = "spuId")
        private String spuId;

        @ApiModelProperty(value = "满件数")
        private String scountRule;

        @ApiModelProperty(value = "满件数")
        private Integer toCount;

        @ApiModelProperty(value = "skuId")
        private String giveSkuId;

        @ApiModelProperty(value = "赠送数量")
        private Integer giveQuantity;

        public static Map<String, List<SkuGive>> initAllGiftToMap(List<SkuGive> gifts) {
            Map<String, List<SkuGive>> allGiveMap = new HashMap<>();
            for (CommonMarketDTO.SkuGive give : gifts) {
                    String spuId = give.getSpuId();
                    //按spu分组满减
                    List<CommonMarketDTO.SkuGive> goodsCut = allGiveMap.get(spuId);
                    if (goodsCut == null) {
                        goodsCut = new ArrayList<>();
                        allGiveMap.put(spuId, goodsCut);
                    }
                    goodsCut.add(new CommonMarketDTO.SkuGive().setSkuId(give.getSkuId()).setSpuId(spuId)
                            .setGiveSkuId(give.getGiveSkuId()).setGiveQuantity(give.getGiveQuantity())
                            .setScountRule(give.getScountRule()).parseRule());
            }
            return allGiveMap;
        }

        //满赠 - 按 toCount 降序
        public static void sort(List<SkuGive> cutRules) {
            cutRules.sort((o1, o2) -> o1.toCount.compareTo(o2.toCount) > 0 ? -1 : 1);
        }

        public SkuGive parseRule() {
            if (StrUtil.isBlank(scountRule)) {
                log.error("spu[" + spuId + "]-["+scountRule+"]-规则错误:[定义为空]");
            }
            if (!NumberUtil.isNumber(scountRule)) {
                log.error("spu[" + spuId + "]-[" + scountRule + "]-规则错误:[" + scountRule + "]满件数不是数字");
            }
            toCount = Integer.parseInt(scountRule);
            if (toCount <= 0) {
                log.error("spu[" + spuId + "]-[" + scountRule + "]-规则错误:[" + scountRule + "]满件数<=0");
            }
            return this;
        }
    }

}
