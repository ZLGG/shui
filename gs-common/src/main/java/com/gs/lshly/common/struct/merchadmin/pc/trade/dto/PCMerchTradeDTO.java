package com.gs.lshly.common.struct.merchadmin.pc.trade.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author oy
* @since 2020-11-16
*/
public abstract class PCMerchTradeDTO implements Serializable {

    @Data
    @ApiModel("PCMerchTradeDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "交易订单表(ID)",hidden = true)
        private String id;

        @ApiModelProperty("会员ID")
        private String userId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String merchantId;

        @ApiModelProperty("来源类型:10:2C,20:2B,30:POS")
        private Integer sourceType;

        @ApiModelProperty("交易编号")
        private String tradeCode;

        @ApiModelProperty("订单状态:10:待支付,20:待发货,30:待收货,40:已完成,50:已取消")
        private Integer tradeState;

        @ApiModelProperty("商品总金额")
        private BigDecimal goodsAmount;

        @ApiModelProperty("优惠金额")
        private BigDecimal discountAmount;

        @ApiModelProperty("运费金额")
        private BigDecimal deliveryAmount;

        @ApiModelProperty("交易总金额")
        private BigDecimal tradeAmount;

        @ApiModelProperty("创建时间")
        private LocalDateTime createTime;

        @ApiModelProperty("支付时间")
        private LocalDateTime payTime;

        @ApiModelProperty("收货时间")
        private LocalDateTime recvTime;

        @ApiModelProperty("支付类型")
        private Integer payType;

        @ApiModelProperty("配送类型")
        private Integer deliveryType;

        @ApiModelProperty("自提码")
        private String takeGoodsCode;

        @ApiModelProperty("自提码图片")
        private String takeGoodsQrcode;

        @ApiModelProperty("收货地址ID")
        private String recvAddresId;

        @ApiModelProperty("收货人")
        private String recvPersonName;

        @ApiModelProperty("收货人电话")
        private String recvPhone;

        @ApiModelProperty("收货地址全文本")
        private String recvFullAddres;

        @ApiModelProperty("是否超时取消")
        private Integer timeoutCancel;

        @ApiModelProperty("买家留言")
        private String buyerRemark;

        @ApiModelProperty("发货备注")
        private String deliveryRemark;

        @ApiModelProperty("隐藏订单:1:是")
        private Boolean isHide;
    }

    @Data
    @ApiModel("PCMerchTradeDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "交易订单表售后(ID)")
        private String id;
    }

    @Data
    @ApiModel("PCMerchTradeDTO.GoodsIdsDTO")
    @AllArgsConstructor
    public static class GoodsIdsDTO extends BaseDTO {

        @ApiModelProperty(value = "商品IDS")
        private List<String> goodsId;
    }
    @Data
    @ApiModel("PCMerchTradeDTO.GoodsIdDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GoodsIdDTO extends BaseDTO {

        @ApiModelProperty(value = "商品id")
        private String goodsId;
    }

    @Data
    @ApiModel("PCMerchTradeDTO.IdDTO")
    @AllArgsConstructor
    public static class orderAmountOrFreight extends BaseDTO {

        @ApiModelProperty(value = "交易订单表售后(ID)")
        private String id;
        @ApiModelProperty(value = "运费")
        private BigDecimal freight;
        @ApiModelProperty(value = "支付金额")
        private BigDecimal orderAmount;
        @ApiModelProperty(value = "改价原因")
        private String  changePriceCause;
    }


}
