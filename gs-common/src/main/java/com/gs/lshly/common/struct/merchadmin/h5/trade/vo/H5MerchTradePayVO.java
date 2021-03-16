package com.gs.lshly.common.struct.merchadmin.h5.trade.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
/**
* @author oy
* @since 2020-11-16
*/
public abstract class H5MerchTradePayVO implements Serializable {

    @Data
    @ApiModel("H5MerchTradePayVO.ListVO")
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

        @ApiModelProperty("支付信息")
        private String payInfo;


        @ApiModelProperty("预下单token")
        private String token;


        @ApiModelProperty("支付单号")
        private String payCode;


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
    @ApiModel("H5MerchTradePayVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
