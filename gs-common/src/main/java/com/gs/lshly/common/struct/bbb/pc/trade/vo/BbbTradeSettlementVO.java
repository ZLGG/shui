package com.gs.lshly.common.struct.bbb.pc.trade.vo;

import com.gs.lshly.common.struct.common.vo.CommonMarketVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
* @author zdf
* @since 2020-10-28
*/
public abstract class BbbTradeSettlementVO implements Serializable {

    @Data
    @ApiModel("BbbTradeSettlementVO.SettlementVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable {
        //返回地址

        @ApiModelProperty("收货地址ID")
        private String recvAddresId;

        @ApiModelProperty("收货人")
        private String recvPersonName;

        @ApiModelProperty("收货人电话")
        private String recvPhone;

        @ApiModelProperty("收货地址全文本")
        private String recvFullAddres;

        //支付配送方式
        //TradePayTypeEnum
        @ApiModelProperty("支付方式(TradePayTypeEnum)")
        private String payType;

        @ApiModelProperty("配送类型(StockDeliveryTypeEnum)")
        private String deliveryType;

        @ApiModelProperty("是否需要发票")
        private Boolean isInvoice;

        //商家
        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("店铺Logo")
        private String shopLogo;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("订单备注")
        private String buyerRemark;

        // 商品总金额、商品数量、运费
        @ApiModelProperty("商品总件数")
        private Integer goodsCount;

        @ApiModelProperty("总总量")
        private Integer totalWeight;

        @ApiModelProperty("商品总金额")
        private BigDecimal goodsAmount;

        @ApiModelProperty("总运费")
        private BigDecimal deliveryAmount;

        @ApiModelProperty("应付总额")
        private BigDecimal tradeAmount;

        @ApiModelProperty(value = "营销活动", hidden = true)
        private CommonMarketVO.ActiveVO marketActiveVO;

        @ApiModelProperty(value = "优惠券", hidden = true)
        private CommonMarketVO.UserCardVO userCardVO;

        // 商品（图片、名称、规格、单价）
        @ApiModelProperty(value = "商品集合")
        private List<BbbTradeSettlementVO.ListVO.goodsInfoVO> goodsInfoVOS;

        @Data
        public static class goodsInfoVO implements Serializable {

            @ApiModelProperty("购物车ID")
            private String cartId;

            @ApiModelProperty("商品ID")
            private String goodsId;

            @ApiModelProperty("商品名称")
            private String goodsName;

            @ApiModelProperty("商品标题")
            private String goodsTitle;

            @ApiModelProperty("SKU ID")
            private String skuId;

            @ApiModelProperty("格规值")
            private String skuSpecValue;

            @ApiModelProperty("SKU IMG")
            private String skuImg;

            @ApiModelProperty("数量")
            private Integer quantity;

            @ApiModelProperty("销售价")
            private BigDecimal salePrice;

            @ApiModelProperty("库存状态[20=正常 30=库存不足]")
            private Integer sockStatus;

            @ApiModelProperty("活动及券后价")
            private BigDecimal activePrice;
        }

    }
}
