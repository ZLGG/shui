package com.gs.lshly.common.struct.platadmin.trade.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author oy
* @since 2020-11-21
*/
public abstract class TradeCancelVO implements Serializable {

    @Data
    @ApiModel("TradeCancelVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("订单ID")
        private String tradeId;


        @ApiModelProperty("订单编号")
        private String tradeCode;


        @ApiModelProperty("会员ID")
        private String userId;

        @ApiModelProperty("会员名字")
        private String userName;


        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("支付ID")
        private String payId;


        @ApiModelProperty("退款金额")
        private BigDecimal tradeAmount;


        @ApiModelProperty("提交申请:10,商家驳回:20,商家确认:30,退款:40,完成:50")
        private Integer cancelState;


        @ApiModelProperty("申请类型:用户取消订单:10,商家取消订单:20")
        private Integer applyType;


        @ApiModelProperty("申请时间")
        private LocalDateTime applyTime;


        @ApiModelProperty("原因类型:")
        private Integer reasonType;


        @ApiModelProperty("原因说明")
        private String remark;


        @ApiModelProperty("拒绝理由")
        private String rejectReason;


        @ApiModelProperty("拒绝时间")
        private LocalDateTime rejectTime;


        @ApiModelProperty("通过时间")
        private LocalDateTime passTime;


        @ApiModelProperty("退款状态:无需退款:10,等待审核:20,等待退款:30,退款成功:40")
        private Integer refundState;


        @ApiModelProperty("退款时间")
        private LocalDateTime refundTime;

        @ApiModelProperty("支付方式:10=支付扫码,20=支付宝APP,30=微信扫码,40=微信公众号,50=微信APP支付,60=微信小程序支付,70=银联PC,80=银联移动,90=线下支付")
        private Integer payType;


    }

    @Data
    @ApiModel("TradeCancelVO.DetailVO")
    public static class DetailVO extends ListVO {

        @ApiModelProperty("商铺名称")
        private String shopName;

        @ApiModelProperty("实付金额")
        private BigDecimal payAmount;

        @ApiModelProperty("商品信息")
        private List<GoodsList> goodsList;

    }
    @Data
    @ApiModel("TradeCancelVO.GoodsList")
    public static class GoodsList implements Serializable {

        @ApiModelProperty("货号")
        private String id;

        @ApiModelProperty("商品名称（名字+SKU规格值）")
        private String goodsName;

        @ApiModelProperty("单价")
        private BigDecimal salaPrice;

        @ApiModelProperty("数量")
        private Integer quantity;

        @ApiModelProperty("商品总额")
        private BigDecimal totalPrice;

    }
    @Data
    @ApiModel("TradeCancelVO.ListVOExport")
    public static class ListVOExport implements Serializable {
        @ApiModelProperty(value = "id", position = 1)
        private String id;


        @ApiModelProperty(value = "订单编号", position = 2)
        private String tradeCode;


        @ApiModelProperty(value = "会员名字", position = 3)
        private String userName;


        @ApiModelProperty(value = "店铺名字", position = 4)
        private String shopName;


        @ApiModelProperty(value = "退款金额", position = 5)
        private BigDecimal tradeAmount;


        @ApiModelProperty(value = "提交申请", position = 6)
        private String cancelState;


        @ApiModelProperty(value = "申请类型", position = 7)
        private String applyType;


        @ApiModelProperty(value = "申请时间", position = 8)
        private LocalDateTime applyTime;


        @ApiModelProperty(value = "原因类型:", position = 9)
        private String reasonType;


        @ApiModelProperty(value = "原因说明", position = 10)
        private String remark;


        @ApiModelProperty(value = "拒绝理由", position = 11)
        private String rejectReason;


        @ApiModelProperty(value = "拒绝时间", position = 12)
        private LocalDateTime rejectTime;


        @ApiModelProperty(value = "通过时间", position = 13)
        private LocalDateTime passTime;


        @ApiModelProperty(value = "退款状态", position = 14)
        private String refundState;


        @ApiModelProperty(value = "退款时间", position = 15)
        private LocalDateTime refundTime;

    }
}
