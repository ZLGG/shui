package com.gs.lshly.common.struct.platadmin.trade.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
* @author oy
* @since 2020-11-16
*/
public abstract class TradeGoodsVO implements Serializable {

    @Data
    @ApiModel("TradeGoodsVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("交易商品ID")
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


        @ApiModelProperty("SKU格规值")
        private String skuSpecValue;


        @ApiModelProperty("SKU图片")
        private String skuImg;


        @ApiModelProperty("sku商品货号")
        private String skuGoodsNo;


        @ApiModelProperty("数量")
        private Integer quantity;


        @ApiModelProperty("销售价")
        private BigDecimal salePrice;


        @ApiModelProperty("优惠金额")
        private BigDecimal discountAmount;


        @ApiModelProperty("支付总金额")
        private BigDecimal payAmount;


        @ApiModelProperty("所得积分")
        private BigDecimal giftIntegral;


        @ApiModelProperty("交易总价占比")
        private BigDecimal tradeAmountPercent;


        @ApiModelProperty("是否允许评论")
        private Integer commentFlag;




    }

    @Data
    @ApiModel("TradeGoodsVO.DetailVO")
    public static class DetailVO extends ListVO {

    }



    @Data
    @ApiModel("TradeGoodsVO.GoodsSaleVO")
    @Accessors(chain = true)
    public static class GoodsSaleVO implements Serializable{


        @ApiModelProperty("商品ID")
        private String goodsId;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("数量")
        private Integer quantity;

        @ApiModelProperty("销售价")
        private BigDecimal salePrice;

    }

    @Data
    @ApiModel("TradeGoodsVO.SumGoodsSaleVO")
    @Accessors(chain = true)
    public static class SumGoodsSaleVO implements Serializable{

        @ApiModelProperty("商品ID")
        private String goodsId;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("数量")
        private Integer quantity;

        @ApiModelProperty("销售价")
        private BigDecimal salePrice;

        @ApiModelProperty("商品ID")
        private List<GoodsSaleVO> goodsSaleVOList;
    }


    @Data
    @ApiModel("TradeGoodsVO.payDateListVO")
    @Accessors(chain = true)
    public static class payDateListVO implements Serializable{

        @ApiModelProperty("新增订单金额")
        private String newOrderMoney;

        @ApiModelProperty("新增订单数量")
        private String newOrderCount;

        @ApiModelProperty("平均客单价")
        private String avgUnivalence;

        @ApiModelProperty("已付款金额金额")
        private Integer payMentOrderMoney;

        @ApiModelProperty("已取消订单数量")
        private BigDecimal payCancelOrderCount;

        @ApiModelProperty("已取消订单数量")
        private BigDecimal payCancelOrderMoney;

    }

}
