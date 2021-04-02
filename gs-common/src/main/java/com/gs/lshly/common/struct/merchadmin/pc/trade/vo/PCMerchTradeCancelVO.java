package com.gs.lshly.common.struct.merchadmin.pc.trade.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
* @since 2020-11-20
*/
public abstract class PCMerchTradeCancelVO implements Serializable {

    @Data
    @ApiModel("PCMerchTradeCancelVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("订单ID")
        private String tradeId;

        @ApiModelProperty("交易编号")
        private String tradeCode;


        @ApiModelProperty("会员ID")
        private String userId;


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
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime applyTime;


        @ApiModelProperty("原因类型:")
        private Integer reasonType;


        @ApiModelProperty("原因说明")
        private String remark;


        @ApiModelProperty("拒绝理由")
        private String rejectReason;


        @ApiModelProperty("拒绝时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime rejectTime;


        @ApiModelProperty("通过时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime passTime;


        @ApiModelProperty("退款状态:无需退款:10,等待审核:20,等待退款:30,退款成功:40")
        private Integer refundState;


        @ApiModelProperty("退款时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime refundTime;

        @ApiModelProperty("交易商品集合")
        List<PCMerchTradeListVO.TradeGoodsVO> tradeGoodsVOS;


    }

    @Data
    @ApiModel("PCMerchTradeCancelVO.DetailVO")
    @Accessors(chain = true)
    public static class DetailVO extends ListVO {
        @ApiModelProperty("收货人")
        private String recvPersonName;

        @ApiModelProperty("收货人电话")
        private String recvPhone;

        @ApiModelProperty("收货地址全文本")
        private String recvFullAddres;

        @ApiModelProperty("运费金额")
        private BigDecimal deliveryAmount;

        @ApiModelProperty("交易总金额")
        private BigDecimal tradeAmount;

        @ApiModelProperty("买家备注")
        private String buyerRemark;

        @ApiModelProperty("用户名")
        private String userName;

        @ApiModelProperty("用户电话")
        private String userPhone;


    }
}
