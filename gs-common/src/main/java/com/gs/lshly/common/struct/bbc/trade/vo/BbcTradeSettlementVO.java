package com.gs.lshly.common.struct.bbc.trade.vo;

import com.gs.lshly.common.struct.common.dto.CommonMarketDTO;
import com.gs.lshly.common.struct.common.vo.CommonMarketVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author oy
* @since 2020-10-28
*/
public abstract class BbcTradeSettlementVO implements Serializable {

    @Data
    @ApiModel("BbcTradeSettlementVO.ListVO")
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

        @ApiModelProperty("联系人")
        private String contactsName;

        @ApiModelProperty("联系人电话")
        private String contactsPhone;
        //商家
        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("店铺Logo")
        private String shopLogo;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("门店配送范围")
        private BigDecimal deliveryScope;

        @ApiModelProperty("省")
        private String shopProvince;

        @ApiModelProperty("市")
        private String shopCity;

        @ApiModelProperty("县区")
        private String shopCounty;

        @ApiModelProperty("街道")
        private String shopStreet;

        @ApiModelProperty("经度")
        private BigDecimal shopLongitude;

        @ApiModelProperty("纬度")
        private BigDecimal shopLatitude;

        @ApiModelProperty("店铺地址")
        private String shopAddress;

        @ApiModelProperty("店铺地址全文本")
        private String shopFullAddres;

        // 商品总金额、商品数量、运费
        @ApiModelProperty("商品总金额")
        private BigDecimal goodsAmount;

        @ApiModelProperty("商品总积分金额")
        private BigDecimal goodsPointAmount;

        @ApiModelProperty("运费金额")
        private BigDecimal deliveryAmount;

        @ApiModelProperty("交易总金额")
        private BigDecimal tradeAmount;

        @ApiModelProperty("交易总积分金额")
        private BigDecimal tradePointAmount;

        @ApiModelProperty("商品合计数量")
        private Integer goodsCount;

        // 商品（图片、名称、规格、单价）
        @ApiModelProperty(value = "商品集合")
        private List<BbcTradeSettlementVO.ListVO.goodsInfoVO> goodsInfoVOS;

        @ApiModelProperty(value = "营销活动")
        private CommonMarketVO.ActiveVO marketActiveVO;

        @ApiModelProperty(value = "优惠券")
        private CommonMarketVO.UserCardVO userCardVO;

        @ApiModelProperty("电信积分")
        private Integer telecomsIntegral;

        @Data
        @ApiModel("BbcTradeSettlementVO.goodsInfoVO")
        @ToString
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

            @ApiModelProperty("是否是积分商品")
            private Boolean isPointGood;

            @ApiModelProperty("销售价")
            private BigDecimal salePrice;

            @ApiModelProperty("活动及券后价")
            private BigDecimal activePrice;

            @ApiModelProperty("积分价格")
            private BigDecimal pointPrice;

            @ApiModelProperty("是否是in会员礼品")
            private Boolean isInMemberGift;

            @ApiModelProperty("in会员积分价格")
            private BigDecimal inMemberPointPrice;

        }

    }
}
