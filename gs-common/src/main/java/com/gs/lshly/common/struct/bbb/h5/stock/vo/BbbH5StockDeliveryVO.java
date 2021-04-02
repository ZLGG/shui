package com.gs.lshly.common.struct.bbb.h5.stock.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

public class BbbH5StockDeliveryVO implements Serializable {
    @Data
    @ApiModel("店铺支持的物流方式-显示对象")
    @Accessors(chain = true)
    public static class SupportDeliveryTypeVO implements Serializable {

        @ApiModelProperty("是否支持快递")
        private Boolean supportExpressDelivery;

        @ApiModelProperty("是否支持门店配送")
        private Boolean supportShopDelivery;

        @ApiModelProperty("门店配送最大距离（km）")
        private Integer shopRanges;

        @ApiModelProperty("是否支持用户自提")
        private Boolean supportUserPickup;

    }
    @Data
    @ApiModel("店铺商品运费计算-显示对象")
    @Accessors(chain = true)
    public static class DeliveryAmountVO implements Serializable {

        @ApiModelProperty(value = "店铺id")
        private String shopId;

        @ApiModelProperty(value = "运费")
        private BigDecimal amount;

        @ApiModelProperty(value = "总重量")
        private BigDecimal totalWeights;

        public static DeliveryAmountVO ofZero(String shopId) {
            return new DeliveryAmountVO().setShopId(shopId).setAmount(BigDecimal.ZERO);
        }

        public static DeliveryAmountVO of(BigDecimal totalCost, String shopId,BigDecimal totalWeights) {
            return new DeliveryAmountVO().setShopId(shopId).setAmount(totalCost).setTotalWeights(totalWeights);
        }
    }

}
