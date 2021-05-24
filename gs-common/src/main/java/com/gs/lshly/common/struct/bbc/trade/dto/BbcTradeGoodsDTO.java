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
 * @since 2020-10-30
 */
public abstract class BbcTradeGoodsDTO implements Serializable {

    @Data
    @ApiModel("BbcTradeGoodsDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "交易商品ID", hidden = true)
        private String id;

        @ApiModelProperty("交易ID")
        private String tradeId;

        @ApiModelProperty("会员ID")
        private String userId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("商家ID")
        private String merchantId;

        @ApiModelProperty("商品ID")
        private String goodsId;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品标题")
        private String goodsTitle;

        @ApiModelProperty("商品货号")
        private String goodsNo;

        @ApiModelProperty("SKU ID")
        private String skuId;

        @ApiModelProperty("格规值")
        private String skuSpecValue;

        @ApiModelProperty("SKU IMG")
        private String skuImg;

        @ApiModelProperty("SKU商品货号")
        private String skuGoodsNo;

        @ApiModelProperty("数量")
        private Integer quantity;

        @ApiModelProperty("销售价")
        private BigDecimal salePrice;

        @ApiModelProperty("支付总金额")
        private BigDecimal payAmount;
        
        @ApiModelProperty("所得积分")
        private BigDecimal giftIntegral;

        @ApiModelProperty("交易总价占比")
        private BigDecimal tradeAmountPercent;

        @ApiModelProperty("是否允许评论")
        private Integer commentFlag;

        @ApiModelProperty("优惠券类型")
        private Integer couponType;
        
        @ApiModelProperty("交易金额")
        private BigDecimal tradeAmount;
    
        @ApiModelProperty("支付总积分金额")
        private BigDecimal tradePointAmount;
        
        @ApiModelProperty("交易金额")
        private BigDecimal discountAmount;
    
        @ApiModelProperty("支付总积分金额")
        private BigDecimal discountPointAmount;
        
        @ApiModelProperty("商品金额")
        private BigDecimal goodsAmount;
        
        @ApiModelProperty("商品金额")
        private BigDecimal goodsPointAmount;
        
        @ApiModelProperty("商品积分金额")
        private BigDecimal payablePointAmount;
        
        @ApiModelProperty("商品金额")
        private BigDecimal payableAmount;

        
    }

    @Data
    @ApiModel("BbcTradeGoodsDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "交易商品ID")
        private String id;
    }


}
