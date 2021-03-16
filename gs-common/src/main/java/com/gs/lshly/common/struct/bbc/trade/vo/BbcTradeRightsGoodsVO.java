package com.gs.lshly.common.struct.bbc.trade.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
/**
* @author oy
* @since 2020-12-06
*/
public abstract class BbcTradeRightsGoodsVO implements Serializable {

    @Data
    @ApiModel("BbcTradeRightsGoodsVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("售后商品表ID")
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
    @ApiModel("BbcTradeRightsGoodsVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
