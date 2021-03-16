package com.gs.lshly.common.struct.merchadmin.h5.trade.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
* @author oy
* @since 2020-11-16
*/
public abstract class H5MerchTradeDeliveryDTO implements Serializable {

    @Data
    @ApiModel("H5MerchTradeDeliveryDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "发货表(ID)",hidden = true)
        private String id;

        @ApiModelProperty("订单ID")
        private String tradeId;

        @ApiModelProperty("会员ID")
        private String userId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String merchantId;

        @ApiModelProperty("订单编号")
        private String orderCode;

        @ApiModelProperty("物流公司id")
        private String logisticsId;

        @ApiModelProperty("物流公司名称")
        private String logisticsName;

        @ApiModelProperty("物流公司编号")
        private String logisticsCode;

        @ApiModelProperty("物流编号")
        private String logisticsNumber;

        @ApiModelProperty("操作员id")
        private String operatorId;

        @ApiModelProperty("操作员姓名")
        private String operatorName;

        @ApiModelProperty("发货时间")
        private LocalDateTime deliveryTime;

        @ApiModelProperty("发货备注")
        private String deliveryRemark;
    }

    @Data
    @ApiModel("H5MerchTradeDeliveryDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "发货表(ID)")
        private String id;
    }


    /**
    * @author oy
    * @since 2020-11-16
    */
    public abstract static class H5MerchTradeDTO implements Serializable {

        @Data
        @ApiModel("H5MerchTradeDTO.ETO")
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
        @ApiModel("H5MerchTradeDTO.IdDTO")
        @AllArgsConstructor
        public static class IdDTO extends BaseDTO {

            @ApiModelProperty(value = "交易订单表(ID)")
            private String id;
        }


    }
    @Data
    @ApiModel("H5MerchTradeDeliveryDTO.deliveryDTO")
    @AllArgsConstructor
    public static class deliveryDTO extends BaseDTO {

        @ApiModelProperty("订单ID")
        private String tradeId;

        @ApiModelProperty("物流公司id")
        private String logisticsId;

        @ApiModelProperty("物流编号")
        private String logisticsNumber;

        @ApiModelProperty("发货备注")
        private String deliveryRemark;

    }
    @Data
    @ApiModel("PCMerchTradeDeliveryDTO.takeGoodsCodeCheckDTO")
    @AllArgsConstructor
    public static class takeGoodsCodeCheckDTO extends BaseDTO {

        @ApiModelProperty("订单ID")
        private String tradeId;

        @ApiModelProperty("自提码")
        private String takeGoodsCode;

    }
}
