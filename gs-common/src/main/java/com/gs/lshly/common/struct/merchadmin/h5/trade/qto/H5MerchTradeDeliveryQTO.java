package com.gs.lshly.common.struct.merchadmin.h5.trade.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
* @author oy
* @since 2020-11-16
*/
public abstract class H5MerchTradeDeliveryQTO implements Serializable {

    @Data
    @ApiModel("H5MerchTradeDeliveryQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("订单ID")
        private String tradeId;

        @ApiModelProperty("会员ID")
        private String userId;

        @ApiModelProperty("交易编号")
        private String tradeCode;

        @ApiModelProperty("订单状态:10:待支付,20:待发货,30:待收货,40:已完成,50:已取消")
        private Integer tradeState;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String merchantId;

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

    /**
    * @author oy
    * @since 2020-11-16
    */
    public abstract static class H5MerchTradeQTO implements Serializable {

        @Data
        @ApiModel("H5MerchTradeQTO.QTO")
        @Accessors(chain = true)
        public static class QTO extends BaseQTO {

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
    }
}
