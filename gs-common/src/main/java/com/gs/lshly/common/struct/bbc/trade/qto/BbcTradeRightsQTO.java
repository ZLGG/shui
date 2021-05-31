package com.gs.lshly.common.struct.bbc.trade.qto;

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
* @since 2020-12-06
*/
public abstract class BbcTradeRightsQTO implements Serializable {

    @Data
    @ApiModel("BbcTradeRightsQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

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

        @ApiModelProperty("退款金额")
        private BigDecimal refundAmount;

        @ApiModelProperty("状态")
        private Integer state;

        @ApiModelProperty("售后类型(10:换货,20:仅退款,30:退货退款)")
        private Integer rightsType;

        @ApiModelProperty("申请售后原因")
        private Integer rightsReasonType;

        @ApiModelProperty("申请售后说明")
        private String rightsRemark;

        @ApiModelProperty("退货方式(10:自行寄回,20:上门取件)")
        private Integer returnType;

        @ApiModelProperty("审核拒绝原因")
        private String rejectReason;

        @ApiModelProperty("申请时间")
        private LocalDateTime applyTime;

        @ApiModelProperty("完成时间")
        private LocalDateTime completeTime;

        @ApiModelProperty("审批人名字")
        private String handPersonName;

        @ApiModelProperty("审批人ID")
        private String merchantAccountId;
    }

    @Data
    @ApiModel("BbcTradeRightsQTO.RightsList")
    @Accessors(chain = true)
    public static class RightsList extends BaseQTO {


/*        @ApiModelProperty("查询类型:10:退货申请,20:退款申请")
        private Integer rightsType;*/


    }

    @Data
    @ApiModel("BbcTradeRightsQTO.RightsList")
    @Accessors(chain = true)
    public static class Rights extends BaseQTO {


        @ApiModelProperty("查询类型:(10:换货,20:仅退款,30:退货退款")
        private Integer rightsType;


    }
}
