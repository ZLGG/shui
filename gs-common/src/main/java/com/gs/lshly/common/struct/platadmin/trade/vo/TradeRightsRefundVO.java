package com.gs.lshly.common.struct.platadmin.trade.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zdf
 * @since 2020-12-22
 */
public abstract class TradeRightsRefundVO implements Serializable {

    @Data
    @ApiModel("TradeRightsRefundVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable {

        @ApiModelProperty("售后退款表ID")
        private String id;


        @ApiModelProperty("售后表ID(售后编号)")
        private String rightsId;


        @ApiModelProperty("订单ID(订单流水号)")
        private String tradeId;


        @ApiModelProperty("退款金额")
        private BigDecimal refundAmount;


        @ApiModelProperty("退款方式(10:线下退款,20:原路返回)")
        private Integer refundType;


        @ApiModelProperty("退款结果(10:成功,20:失败)")
        private Integer state;


        @ApiModelProperty("退款结果信息")
        private String resultInfo;


        @ApiModelProperty("退款银行名字")
        private String refundBankName;


        @ApiModelProperty("退款银行账户")
        private String refundAccount;


        @ApiModelProperty("退款人")
        private String refundName;


        @ApiModelProperty("收款银行名字")
        private String collectBankName;


        @ApiModelProperty("收款银行账户")
        private String collectAccount;


        @ApiModelProperty("收款人")
        private String collectName;

    }

    @Data
    @ApiModel("TradeRightsRefundVO.DetailVO")
    public static class DetailVO implements Serializable {

        /*        @ApiModelProperty("支付方式[90=线下支付]")
                private Integer applyType;

                @ApiModelProperty("支付状态")
                private Integer applyState;

                @ApiModelProperty("支付开始时间")
                @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
                private LocalDateTime cdate;*/
        @ApiModelProperty("售后退款表ID")
        private String id;

        @ApiModelProperty("支付方式")
        private Integer payType;

        @ApiModelProperty("手机号码")
        private String phone;

        @ApiModelProperty("订单编号")
        private String tradeCode;

        @ApiModelProperty("实退积分")
        private BigDecimal refundPoint;

        @ApiModelProperty("订单ID(订单流水号)")
        private String tradeId;

        @ApiModelProperty("实退金额")
        private BigDecimal refundAmount;
    }

    @Data
    @ApiModel("TradeRightsRefundVO.DetailViewVO")
    public static class DetailViewVO extends ListVO {
        @ApiModelProperty("支付方式")
        private Integer payType;

        @ApiModelProperty("手机号码")
        private String phone;

        @ApiModelProperty("实退积分")
        private BigDecimal refundPoint;

        @ApiModelProperty("所属商家")
        private String shopName;

        @ApiModelProperty("售后申请时间")
        private LocalDateTime applyTime;

        @ApiModelProperty("退款订单详情")
        List<TradeListVO.TradeRightsGoodsDetailViewVO> detailViewVOList;

/*        @ApiModelProperty("支付开始时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;

        @ApiModelProperty("支付完成时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime udate;*/

    }
}
