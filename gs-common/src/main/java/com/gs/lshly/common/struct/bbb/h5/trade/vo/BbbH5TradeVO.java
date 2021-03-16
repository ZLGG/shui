package com.gs.lshly.common.struct.bbb.h5.trade.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
* @author oy
* @since 2020-10-28
*/
public abstract class BbbH5TradeVO implements Serializable {

    @Data
    @ApiModel("BbcTradeVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("交易订单号(ID)")
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


        @ApiModelProperty("交易状态")
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
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createTime;


        @ApiModelProperty("支付时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime payTime;


        @ApiModelProperty("收货时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
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

        @ApiModelProperty("是否隐藏订单:1:是")
        private Integer isHide;

    }

    @Data
    @ApiModel("BbcTradeVO.DetailVO")
    public static class DetailVO extends ListVO {

    }

}
