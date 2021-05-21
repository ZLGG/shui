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

        @ApiModelProperty(value = "交易支付表ID",position = 1)
        private String id;


        @ApiModelProperty(value = "订单ID",position = 2)
        private String tradeId;


        @ApiModelProperty(value = "会员ID",position = 3)
        private String userId;


        @ApiModelProperty(value = "店铺ID",position = 4)
        private String shopId;


        @ApiModelProperty(value = "商家ID",position = 5)
        private String merchantId;


        @ApiModelProperty(value = "交易编号",position = 6)
        private String tradeCode;


        @ApiModelProperty(value = "预下单token",position = 7)
        private String token;


        @ApiModelProperty(value = "支付单号",position = 8)
        private String payCode;

        @ApiModelProperty(value = "支付信息",position = 9)
        private String payInfo;


        @ApiModelProperty(value = "支付方式:10=支付扫码,20=支付宝APP,30=微信扫码,40=微信公众号,50=微信APP支付,60=微信小程序支付,70=银联PC,80=银联移动,90=线下支付",position = 10)
        private Integer payType;


        @ApiModelProperty(value = "支付状态:待支付/待确认/驳回/已支付",position = 11)
        private Integer payState;


        @ApiModelProperty(value = "订单总金额",position = 12)
        private BigDecimal totalAmount;


        @ApiModelProperty(value = "客户端IP地址",position = 13)
        private String clientIp;

        @ApiModelProperty(value = "会员名字",position = 14)
        private String userName;

        @ApiModelProperty(value = "店铺名字",position = 15)
        private String shopName;


    }
    @Data
    @ApiModel("TradePayVO.ListVOExport")
    @Accessors(chain = true)
    public static class ListVOExport implements Serializable{

        @ApiModelProperty(value = "交易支付表ID",position = 1)
        private String id;


        @ApiModelProperty(value = "订单ID",position = 2)
        private String tradeId;


        @ApiModelProperty(value = "会员名称",position = 3)
        private String userName;


        @ApiModelProperty(value = "店铺名字",position = 4)
        private String shopName;



        @ApiModelProperty(value = "交易编号",position = 5)
        private String tradeCode;


        @ApiModelProperty(value = "支付信息",position = 6)
        private String payInfo;


        @ApiModelProperty(value = "支付方式:10=支付扫码,20=支付宝APP,30=微信扫码,40=微信公众号,50=微信APP支付,60=微信小程序支付,70=银联PC,80=银联移动,90=线下支付",position = 7)
        private String payType;


        @ApiModelProperty(value = "支付状态:待支付/待确认/驳回/已支付",position = 8)
        private String payState;


        @ApiModelProperty(value = "订单总金额",position = 9)
        private BigDecimal totalAmount;


        @ApiModelProperty(value = "客户端IP地址",position = 10)
        private String clientIp;


    }

    @Data
    @ApiModel("TradePayVO.DetailVO")
    public static class DetailVO extends ListVO {
        @ApiModelProperty("支付账户")
        private String payAccount;
        @ApiModelProperty("支付网关费用")
        private BigDecimal payAPIAmount;
        @ApiModelProperty("第三方交易流水号")
        private BigDecimal OtherPayNum;
        @ApiModelProperty("第三方支付账户")
        private BigDecimal OtherPayAccount;
    }
    @Data
    @ApiModel("TradePayVO.RelationDetailVO")
    public static class RelationDetailVO implements Serializable{

        @ApiModelProperty(value = "支付方式:10=支付扫码,20=支付宝APP,30=微信扫码,40=微信公众号,50=微信APP支付,60=微信小程序支付,70=银联PC,80=银联移动,90=线下支付",position = 1)
        private Integer payType;

        @ApiModelProperty(value = "交易支付表ID",position = 2)
        private String id;


        @ApiModelProperty(value = "订单ID",position = 3)
        private String tradeId;

        @ApiModelProperty(value = "支付状态:待支付/待确认/驳回/已支付",position = 4)
        private Integer payState;


        @ApiModelProperty(value = "订单总金额",position = 5)
        private BigDecimal totalAmount;

        @ApiModelProperty(value = "支付开始时间",position = 6)
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;

        @ApiModelProperty(value = "修改时间",position = 7)
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime udate;


        @ApiModelProperty(value = "支付完成时间",position = 8)
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime finishDate;

        @ApiModelProperty(value = "订单号",position = 9)
        private String tradeCode;

        @ApiModelProperty(value = "用户手机号",position = 10)
        private String phone;

    }
    @Data
    @ApiModel("TradePayVO.RelationDetailExport")
    public static class RelationDetailExport implements Serializable{

        @ApiModelProperty(value = "支付方式:10=支付扫码,20=支付宝APP,30=微信扫码,40=微信公众号,50=微信APP支付,60=微信小程序支付,70=银联PC,80=银联移动,90=线下支付",position = 1)
        private String  payType;

        @ApiModelProperty(value = "交易支付表ID",position = 2)
        private String id;


        @ApiModelProperty(value = "订单ID",position = 3)
        private String tradeId;

        @ApiModelProperty(value = "支付状态:待支付/待确认/驳回/已支付",position = 4)
        private String  payState;


        @ApiModelProperty(value = "订单总金额",position = 5)
        private BigDecimal totalAmount;

        @ApiModelProperty(value = "支付开始时间",position = 6)
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;

        @ApiModelProperty(value = "修改时间",position = 7)
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime udate;


        @ApiModelProperty(value = "支付完成时间",position = 8)
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime finishDate;

    }
}
