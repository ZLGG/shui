package com.gs.lshly.biz.support.stock.service.common;

import com.gs.lshly.common.struct.common.stock.CommonDeliveryCostCalcParam;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateVO;

import java.math.BigDecimal;

/**
 * 运费计算器
 * @author lxus
 * @since 2020-11-18
 */
public interface ICommonDeliveryCostCalculator {

    BigDecimal calculate(CommonStockTemplateVO.SkuAmountAndPriceVO sku, CommonDeliveryCostCalcParam calcParam);
}
