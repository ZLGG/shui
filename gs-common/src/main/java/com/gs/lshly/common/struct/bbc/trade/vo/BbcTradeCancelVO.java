package com.gs.lshly.common.struct.bbc.trade.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
* @author oy
* @since 2020-11-20
*/
public abstract class BbcTradeCancelVO implements Serializable {

    @Data
    @ApiModel("BbcTradeCancelVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("订单ID")
        private String tradeId;

        @ApiModelProperty("订单Code")
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




    }

    @Data
    @ApiModel("BbcTradeCancelVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
