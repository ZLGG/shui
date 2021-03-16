package com.gs.lshly.common.struct.bbc.trade.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
/**
* @author oy
* @since 2020-12-06
*/
public abstract class BbcTradeRightsGoodsDTO implements Serializable {

    @Data
    @ApiModel("BbcTradeRightsGoodsDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "售后商品表ID",hidden = true)
        private String id;

        @ApiModelProperty("售后表ID")
        private String rightsId;

        @ApiModelProperty("订单ID")
        private String tradeId;

        @ApiModelProperty("订单商品ID")
        private String tradeGoodsId;

        @ApiModelProperty("会员ID")
        private String userId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String merchantId;

        @ApiModelProperty("订单编号")
        private String orderCode;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("SKU ID")
        private String skuId;

        @ApiModelProperty("格规值")
        private String skuSpecValue;

        @ApiModelProperty("售后数量")
        private Integer quantity;

        @ApiModelProperty("销售价")
        private BigDecimal salePrice;

        @ApiModelProperty("退款金额")
        private BigDecimal refundAmount;
    }

    @Data
    @ApiModel("BbcTradeRightsGoodsDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "售后商品表ID")
        private String id;
    }


}
