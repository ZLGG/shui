package com.gs.lshly.biz.support.stock.service.common.impl;

import com.gs.lshly.biz.support.stock.service.common.ICommonDeliveryCostCalculator;
import com.gs.lshly.common.enums.stock.StockDeliveryCostCalcWayEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.common.stock.CommonDeliveryCostCalcParam;
import com.gs.lshly.common.struct.common.stock.CommonStockTemplateVO;
import com.gs.lshly.common.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 物流运费计算器
 * @author lxus
 * @since 2020-11-18
 */
@Service
@Slf4j
public class CommonDeliveryCostCalculator implements ICommonDeliveryCostCalculator {

    @Override
    public BigDecimal calculate(CommonStockTemplateVO.SkuAmountAndPriceVO sku, CommonDeliveryCostCalcParam calcParam) {
        if (StockDeliveryCostCalcWayEnum.固定运费 == calcParam.getCalcWay()) {
            log.info("计算结果-固定运费:" + calcParam.getCustomizeCost());
            return calcParam.getCustomizeCost();
        }
        if (StockDeliveryCostCalcWayEnum.按金额 == calcParam.getCalcWay()) {
            log.info("计算结果-按金额范围:" + calcParam.getStepPriceParam());
            return calcParam.getStepPriceParam();
        }
        if (StockDeliveryCostCalcWayEnum.按件数 == calcParam.getCalcWay() || StockDeliveryCostCalcWayEnum.按重量 == calcParam.getCalcWay()) {
            String msgUnit = StockDeliveryCostCalcWayEnum.按件数 == calcParam.getCalcWay() ? "件" : "重";
            BigDecimal quantity = StockDeliveryCostCalcWayEnum.按件数 == calcParam.getCalcWay() ? sku.getAmount() : sku.getWeight();
            CommonDeliveryCostCalcParam.QuantityParam param = calcParam.getQuantityParam();
            if (quantity.compareTo(param.getFirst()) <= 0) {
                log.info("计算结果-按{}-首{}[{}]-首费[{}]:实际[{}]{}-实费{}",
                        msgUnit, msgUnit, param.getFirst(), param.getFirstPrice(), quantity, msgUnit, param.getFirstPrice());
                return param.getFirstPrice();
            } else {
                //首费
                BigDecimal totalCost = param.getFirstPrice();
                //续件、续重
                BigDecimal increase = quantity.subtract(param.getFirst());
                //续次数
                int increaseTimes=0;
                if (param.getIncrease().compareTo(BigDecimal.ZERO)==0){
                    increaseTimes=0;
                }else {
                    increaseTimes = increase.divide(param.getIncrease(), 1, RoundingMode.UP).intValue();
                }
                //不能整除
                if (new BigDecimal(increaseTimes + "").multiply(param.getIncrease()).compareTo(increase) < 0) {
                    increaseTimes++;
                }
                totalCost = totalCost.add(param.getIncreasePrice().multiply(new BigDecimal(increaseTimes + "")));

                log.info("计算结果-按{}-首{}[{}]-首费[{}]-续{}[{}]-续费[{}]:实际[{}]{}-实费{}",
                        msgUnit, msgUnit, param.getFirst(), param.getFirstPrice(), msgUnit, param.getIncrease(), param.getIncreasePrice(), quantity, msgUnit, totalCost);
                return totalCost;
            }
        }
        throw new BusinessException(String.format("运费计算错误：计算参数：%s，sku参数：%s", JsonUtils.toJson(calcParam), JsonUtils.toJson(sku)));
    }

    public static void main(String[] args) {
        CommonDeliveryCostCalculator calculator = new CommonDeliveryCostCalculator();
        CommonStockTemplateVO.SkuAmountAndPriceVO sku = new CommonStockTemplateVO.SkuAmountAndPriceVO()
                //总价
                .setPrice(new BigDecimal("236.1"))
                //总重
                .setWeight(new BigDecimal("15"))
                //总数量
                .setAmount(new BigDecimal("10"));
//        //固定运费
        CommonDeliveryCostCalcParam calcParam = new CommonDeliveryCostCalcParam()
                .setCalcWay(StockDeliveryCostCalcWayEnum.固定运费).setCustomizeCost(new BigDecimal("11.1"));
        System.out.println(calculator.calculate(sku, calcParam));
        //按金额
        calcParam = new CommonDeliveryCostCalcParam()
                .setCalcWay(StockDeliveryCostCalcWayEnum.按金额).setStepPriceParam(new BigDecimal("22.2"));
        System.out.println(calculator.calculate(sku, calcParam));
        //按件数
        CommonDeliveryCostCalcParam.QuantityParam param = new CommonDeliveryCostCalcParam.QuantityParam();
        param.setFirst(new BigDecimal("3")).setFirstPrice(new BigDecimal("2")).setIncrease(new BigDecimal("4")).setIncreasePrice(new BigDecimal("5"));
        calcParam = new CommonDeliveryCostCalcParam().setCalcWay(StockDeliveryCostCalcWayEnum.按件数).setQuantityParam(param);
        System.out.println(calculator.calculate(sku, calcParam));
        //按重量
        param = new CommonDeliveryCostCalcParam.QuantityParam();
        param.setFirst(new BigDecimal("3")).setFirstPrice(new BigDecimal("3")).setIncrease(new BigDecimal("6")).setIncreasePrice(new BigDecimal("2"));
        calcParam = new CommonDeliveryCostCalcParam().setCalcWay(StockDeliveryCostCalcWayEnum.按重量).setQuantityParam(param);
        System.out.println(calculator.calculate(sku, calcParam));
    }


}
