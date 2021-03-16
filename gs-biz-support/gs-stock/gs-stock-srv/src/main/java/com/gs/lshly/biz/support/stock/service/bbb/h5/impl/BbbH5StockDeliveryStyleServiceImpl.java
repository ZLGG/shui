package com.gs.lshly.biz.support.stock.service.bbb.h5.impl;

import com.gs.lshly.biz.support.stock.service.bbb.h5.IBbbH5StockDeliveryStyleService;
import com.gs.lshly.biz.support.stock.service.common.ICommonDeliveryCostCalculator;
import com.gs.lshly.biz.support.stock.service.common.ICommonStockTemplateService;
import com.gs.lshly.common.enums.StockDeliveryTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.bbb.h5.stock.dto.BbbH5StockDeliveryDTO;
import com.gs.lshly.common.struct.bbb.h5.stock.vo.BbbH5StockDeliveryVO;
import com.gs.lshly.common.struct.common.stock.CommonDeliveryCostCalcParam;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateVO;
import com.gs.lshly.rpc.api.bbb.h5.commodity.IBbbH5GoodsSkuRpc;
import com.gs.lshly.rpc.api.bbb.h5.merchant.IBbbH5ShopRpc;
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
public class BbbH5StockDeliveryStyleServiceImpl implements IBbbH5StockDeliveryStyleService {

    @Autowired
    private ICommonStockTemplateService stockTemplateService;
    @Autowired
    private ICommonDeliveryCostCalculator calculator;
    @DubboReference
    private IBbbH5GoodsSkuRpc skuRpc;
    @DubboReference
    private IBbbH5ShopRpc shopRpc;

    @Override
    public BbbH5StockDeliveryVO.DeliveryAmountVO calculate(BbbH5StockDeliveryDTO.DeliveryAmountDTO dto) {

        if (StockDeliveryTypeEnum.门店自提.getCode().equals(dto.getDeliveryType())) {
            log.info("门店自提");
            return BbbH5StockDeliveryVO.DeliveryAmountVO.ofZero(dto.getShopId());
        }

        if (StockDeliveryTypeEnum.快递配送.getCode().equals(dto.getDeliveryType())) {
            log.info("快递配送");
            //相同sku模板需要合并数量及价格
            Map<String, CommonStockTemplateVO.TemplateIdAndSkuPriceVO> templateSkuMap = new HashMap<>();
            for (BbbH5StockDeliveryDTO.DeliverySKUDTO sku : dto.getSkus()) {
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
            return BbbH5StockDeliveryVO.DeliveryAmountVO.of(totalCost, dto.getShopId());
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
        log.info("：包含以下%s种sku；", StringUtils.isEmpty(templateId) ? "门店配送" : String.format("运费模板id=%s；name=%s", templateId, templateName), skus.size());
        for(CommonStockTemplateVO.SkuAmountAndPriceVO vo : skus){
            merged.setAmount(merged.getAmount() != null ? merged.getAmount().add(vo.getAmount()) : vo.getAmount());
            BigDecimal voWeight = vo.getAmount().multiply(vo.getWeight());
            merged.setWeight(merged.getWeight() != null ? merged.getWeight().add(voWeight) : voWeight);
            BigDecimal voPrice = vo.getAmount().multiply(vo.getPrice());
            merged.setPrice(merged.getPrice() != null ? merged.getPrice().add(voPrice) : voPrice);
            log.info("\tskuId:%s,重量:%s,单价:%s；数量:%s,总重量:%s,总价:%s", vo.getSkuId(), vo.getWeight(), vo.getPrice(), vo.getAmount(), voWeight, voPrice);
        }
        log.info("合并后：总重量:%s,总数量:%s,总价格:%s", merged.getWeight(), merged.getAmount(), merged.getPrice());
        return merged;
    }

}
