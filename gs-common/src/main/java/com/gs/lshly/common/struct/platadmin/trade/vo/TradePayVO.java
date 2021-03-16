package com.gs.lshly.common.struct.platadmin.trade.vo;

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
* @since 2020-11-16
*/
public abstract class TradePayVO implements Serializable {

    @Data
    @ApiModel("TradePayVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("交易支付表ID")
        private String id;


        @ApiModelProperty("订单ID")
        private String tradeId;


        @ApiModelProperty("会员ID")
        private String userId;


        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("商家ID")
        private String merchantId;


        @ApiModelProperty("交易编号")
        private String tradeCode;


        @ApiModelProperty("预下单token")
        private String token;


        @ApiModelProperty("支付单号")
        private String payCode;

        @ApiModelProperty("支付信息")
        private String payInfo;


        @ApiModelProperty("支付方式:10=支付扫码,20=支付宝APP,30=微信扫码,40=微信公众号,50=微信APP支付,60=微信小程序支付,70=银联PC,80=银联移动,90=线下支付")
        private Integer payType;


        @ApiModelProperty("支付状态:待支付/待确认/驳回/已支付")
        private Integer payState;


        @ApiModelProperty("订单总金额")
        private BigDecimal totalAmount;


        @ApiModelProperty("客户端IP地址")
        private String clientIp;


    }

    @Data
    @ApiModel("TradePayVO.DetailVO")
    public static class DetailVO extends ListVO {
        @ApiModelProperty("订单所属会员")
        private String userName;
        @ApiModelProperty("支付账户")
        private String payAccount;
        @ApiModelProperty("支付网关费用")
        private BigDecimal payAPIAmount;
        @ApiModelProperty("第三方交易流水号")
        private BigDecimal OtherPayNum;
        @ApiModelProperty("第三方支付账户")
        private BigDecimal OtherPayAccount;
    }@Data
    @ApiModel("TradePayVO.RelationDetailVO")
    public static class RelationDetailVO implements Serializable{

        @ApiModelProperty("支付方式:10=支付扫码,20=支付宝APP,30=微信扫码,40=微信公众号,50=微信APP支付,60=微信小程序支付,70=银联PC,80=银联移动,90=线下支付")
        private Integer payType;

        @ApiModelProperty("交易支付表ID")
        private String id;


        @ApiModelProperty("订单ID")
        private String tradeId;

        @ApiModelProperty("支付状态:待支付/待确认/驳回/已支付")
        private Integer payState;


        @ApiModelProperty("订单总金额")
        private BigDecimal totalAmount;

        @ApiModelProperty("支付开始时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;

        @ApiModelProperty("修改时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime udate;


        @ApiModelProperty("支付完成时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime finishDate;

    }
}
