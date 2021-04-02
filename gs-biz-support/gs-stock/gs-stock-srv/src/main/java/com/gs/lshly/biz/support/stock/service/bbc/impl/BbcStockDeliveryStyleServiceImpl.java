package com.gs.lshly.biz.support.stock.service.bbc.impl;

import com.gs.lshly.biz.support.stock.service.bbc.IBbcStockDeliveryStyleService;
import com.gs.lshly.biz.support.stock.service.common.ICommonDeliveryCostCalculator;
import com.gs.lshly.biz.support.stock.service.common.ICommonStockTemplateService;
import com.gs.lshly.common.enums.StockDeliveryTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.bbc.stock.dto.BbcStockDeliveryDTO;
import com.gs.lshly.common.struct.bbc.stock.vo.BbcStockDeliveryVO;
import com.gs.lshly.common.struct.common.stock.CommonDeliveryCostCalcParam;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateVO;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsSkuRpc;
import com.gs.lshly.rpc.api.bbc.merchant.IBbcShopRpc;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* <p>
*  门店物流服务实现类
* </p>
* @author lxus
* @since 2020-11-02
*/
@Component
@Slf4j
public class BbcStockDeliveryStyleServiceImpl implements IBbcStockDeliveryStyleService {

    @Autowired
    private ICommonStockTemplateService stockTemplateService;
    @Autowired
    private ICommonDeliveryCostCalculator calculator;

    @DubboReference
    private IBbcGoodsSkuRpc skuRpc;

    @DubboReference
    private IBbcShopRpc shopRpc;

    @Override
    public BbcStockDeliveryVO.DeliveryAmountVO calculate(BbcStockDeliveryDTO.DeliveryAmountDTO dto) {

        if (StockDeliveryTypeEnum.门店自提.getCode().equals(dto.getDeliveryType())) {
            log.info("门店自提");
            return BbcStockDeliveryVO.DeliveryAmountVO.ofZero(dto.getShopId());
        }

        if (StockDeliveryTypeEnum.快递配送.getCode().equals(dto.getDeliveryType())) {
            log.info("快递配送");
            //相同sku模板需要合并数量及价格
            Map<String, CommonStockTemplateVO.TemplateIdAndSkuPriceVO> templateSkuMap = new HashMap<>();
            for (BbcStockDeliveryDTO.DeliverySKUDTO sku : dto.getSkus()) {
                if("gift".equals(sku.getSkuId())){
                    log.info("赠品算不算运费?");
                    continue;
                }
                //通过查询配置，确定唯一运费模板
                CommonStockTemplateVO.TemplateIdAndSkuPriceVO templateIdAndSkuPrice = skuRpc.getGoodsStockTemplateIdAndSkuPrice(sku);
                //相同运费模板需要合并数量及价格
                CommonStockTemplateVO.TemplateIdAndSkuPriceVO mergedSku = templateSkuMap.get(templateIdAndSkuPrice.getTemplateId());
                if (mergedSku == null) {
                    mergedSku = templateIdAndSkuPrice;
                    templateSkuMap.put(templateIdAndSkuPrice.getTemplateId(), mergedSku);
                } else {
                    mergedSku.getSkus().addAll(templateIdAndSkuPrice.getSkus());
                }
            }
            BigDecimal totalCost = BigDecimal.ZERO;
            for(CommonStockTemplateVO.TemplateIdAndSkuPriceVO skuPriceVO : templateSkuMap.values()) {
                //通过发货地址，生成计算模板
                CommonStockTemplateVO.ListDetailVO stockTemplateDetail = stockTemplateService.getDetail(skuPriceVO.getTemplateId());
                //合并sku数量、价格、重量后，计算运费
                CommonStockTemplateVO.SkuAmountAndPriceVO mergedVO = merge(stockTemplateDetail.getId(), stockTemplateDetail.getTemplateName(), skuPriceVO.getSkus());
                CommonDeliveryCostCalcParam calcParam = stockTemplateService.parseToDeliveryCostCalcParam(mergedVO, stockTemplateDetail, dto.getAddressId());
                //通过计价数量，及计算模板，计算运费
                BigDecimal cost = calculator.calculate(mergedVO, calcParam);
                totalCost = totalCost.add(cost);
            }
            return BbcStockDeliveryVO.DeliveryAmountVO.of(totalCost, dto.getShopId());
        }

        if (StockDeliveryTypeEnum.门店配送.getCode().equals(dto.getDeliveryType())) {
            log.info("门店配送");
            //通过店铺选择的配送方式，确定商品计价参数
            CommonDeliveryCostCalcParam calcParam = shopRpc.getPickupDeliveryCostCalcParam(dto.getShopId());
            //通过计价数量，及计算模板，计算运费
            List<CommonStockTemplateVO.SkuAmountAndPriceVO> skus = new ArrayList<>();
            for(BbcStockDeliveryDTO.DeliverySKUDTO sku : dto.getSkus()){
                skus.add(skuRpc.getGoodsSkuPrice(sku));
            }
            //合并sku数量、价格、重量后，计算运费
            BigDecimal totalCost =  calculator.calculate(merge("","", skus), calcParam);
            return BbcStockDeliveryVO.DeliveryAmountVO.of(totalCost, dto.getShopId());
        }
        throw new BusinessException("配送方式错误");
    }

    /**
     * 合并sku数量、价格、重量
     * @param skus
     * @return
     */
    private CommonStockTemplateVO.SkuAmountAndPriceVO merge(String templateId, String templateName, List<CommonStockTemplateVO.SkuAmountAndPriceVO> skus) {
        CommonStockTemplateVO.SkuAmountAndPriceVO merged = new CommonStockTemplateVO.SkuAmountAndPriceVO();
        log.info("：包含以下{}种sku；", StringUtils.isEmpty(templateId) ? "门店配送" : String.format("运费模板id=%s；name=%s", templateId, templateName), skus.size());
        for(CommonStockTemplateVO.SkuAmountAndPriceVO vo : skus){
            merged.setAmount(merged.getAmount() != null ? merged.getAmount().add(vo.getAmount()) : vo.getAmount());
            BigDecimal voWeight=BigDecimal.ZERO;
            if (vo.getAmount()!=null && vo.getWeight()!=null){
               voWeight = vo.getAmount().multiply(vo.getWeight());
            }
            merged.setWeight(merged.getWeight() != null ? merged.getWeight().add(voWeight) : voWeight);
            BigDecimal voPrice =BigDecimal.ZERO;
            if (vo.getAmount()!=null && vo.getPrice()!=null){
                voPrice = vo.getAmount().multiply(vo.getPrice());
            }
            merged.setPrice(merged.getPrice() != null ? merged.getPrice().add(voPrice) : voPrice);
            log.info("\tskuId:{},重量:{},单价:{}；数量:{},总重量:{},总价:{}", vo.getSkuId(), vo.getWeight(), vo.getPrice(), vo.getAmount(), voWeight, voPrice);
        }
        log.info("合并后：总重量:{},总数量:{},总价格:{}", merged.getWeight(), merged.getAmount(), merged.getPrice());
        return merged;
    }

}
