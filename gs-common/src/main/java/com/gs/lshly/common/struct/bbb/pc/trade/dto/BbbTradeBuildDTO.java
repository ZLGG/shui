package com.gs.lshly.common.struct.bbb.pc.trade.dto;

import com.gs.lshly.common.enums.ActivityTerminalEnum;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.vo.CommonMarketVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
* @author zdf
* @since 2020-10-28
*/
public abstract class BbbTradeBuildDTO implements Serializable {

    @Data
    @ApiModel(value = "BbbTradeBuildDTO.TradeBuildDTO")
    @Accessors(chain = true)
    public static class DTO  extends BaseDTO implements Serializable {

        @ApiModelProperty(value = "店铺ID")
        private String shopId;

        @ApiModelProperty(value = "收货地址ID")
        private String addressId;


        @ApiModelProperty(value = "买家留言")
        private String buyerRemark;

        //支付配送方式
        //TradePayTypeEnum
        @ApiModelProperty("支付方式(TradePayTypeEnum)")
        private Integer payType;

        @ApiModelProperty("配送类型(StockDeliveryTypeEnum)")
        private Integer deliveryType;

        @ApiModelProperty("是否需要发票")
        private Boolean isInvoice;

        @ApiModelProperty("发票ID")
        private String invoiceId;

        @ApiModelProperty(value = "运费金额")
        private BigDecimal deliveryAmount;

        @ApiModelProperty(value = "商品信息")
        private List<ProductData> productData;

        @ApiModelProperty(value = "业务来源端", hidden = true)
        private ActivityTerminalEnum terminal;

        @ApiModelProperty(value = "营销活动", hidden = true)
        private CommonMarketVO.ActiveVO marketActiveVO;

        @ApiModelProperty(value = "优惠券", hidden = true)
        private CommonMarketVO.UserCardVO userCardVO;

        @ApiModelProperty(value = "订单支付金额", hidden = true)
        BigDecimal shopProductAmount;

        @Data
        @ApiModel(value = "BbbTradeBuildDTO.ProductData")
        public static class ProductData implements Serializable {
            @ApiModelProperty(value = "购物车ID")
            private String cartId;

            @ApiModelProperty(value = "商品Id")
            private String goodsId;

            @ApiModelProperty(value = "sku商品Id")
            private String goodsSkuId;

            @ApiModelProperty(value = "购买数量")
            private Integer quantity;
        }

    }

    @Data
    @ApiModel("BbbTradeBuildDTO.cartIdsDTO")
    @Accessors(chain = true)
    public static class cartIdsDTO extends BaseDTO {

        @ApiModelProperty(value = "购物车ID数组")
        private List<String> cartIds;

        @ApiModelProperty(value = "配送类型")
        private Integer deliveryType;

        @ApiModelProperty(value = "sku商品Id")
        private String goodsSkuId;

        @ApiModelProperty(value = "购买数量")
        private Integer quantity;

        @ApiModelProperty(value = "收货地址ID")
        private String addressId;

        @ApiModelProperty(value = "业务来源端", hidden = true)
        private ActivityTerminalEnum terminal;
    }


}
