package com.gs.lshly.common.struct.platadmin.trade.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zdf
 * @since 2020-12-22
 */
public abstract class TradeRightsDTO implements Serializable {

    @Data
    @ApiModel("TradeRightsDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "售后表ID", hidden = true)
        private String id;

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

        @ApiModelProperty("退款备注")
        private String refundRemarks;

        @ApiModelProperty("退款金额")
        private BigDecimal refundAmount;

        @ApiModelProperty("状态(10:申请,20:驳回,30:通过,40:已退货,50:收到退货,60:等待退款,70:退款完成,80:等待发货,90:已发货,91:确认收货,95:用户取消,99:完成)")
        private Integer state;

        @ApiModelProperty("售后类型(10:换货,20:仅退款,30:退货退款)")
        private Integer rightsType;

        @ApiModelProperty("申请售后原因(10:质量问题,20:7天无理由,30:地址信息填写错误,40:不想要了,50:价格有点贵,60:商品错选,70:商品无货,80:其他)")
        private Integer rightsReasonType;

        @ApiModelProperty("申请售后说明")
        private String rightsRemark;

        @ApiModelProperty("退货方式(10:自行寄回,20:上门取件)")
        private Integer returnType;

        @ApiModelProperty("退货物流公司名字")
        private String returnGoodsLogisticsName;

        @ApiModelProperty("退货物流单号")
        private String returnGoodsLogisticsNum;

        @ApiModelProperty("退货寄出时间")
        private LocalDateTime returnGoodsLogisticsDate;

        @ApiModelProperty("换货商机寄回物流公司名字")
        private String sendBackLogisticsName;

        @ApiModelProperty("换货商机寄回物流单号")
        private String sendBackLogisticsNum;

        @ApiModelProperty("换货商机寄出时间")
        private LocalDateTime sendBackLogisticsDate;

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
    @ApiModel("TradeRightsDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "售后表ID")
        private String id;
    }

    @Data
    @ApiModel("TradeRightsDTO.PlatformCheckReasonDTO")
    @AllArgsConstructor
    public static class PlatformCheckReasonDTO extends BaseDTO {

        @ApiModelProperty(value = "售后表ID")
        private String id;

        @ApiModelProperty(value = "平台处理说明")
        private String platformCheckReason;
    }

    @Data
    @ApiModel("TradeRightsDTO.RefundDTO")
    @AllArgsConstructor
    public static class RefundDTO extends BaseDTO {

        @ApiModelProperty(value = "售后表ID")
        private String id;
        @ApiModelProperty("退款银行名字")
        private String refundBankName;

        @ApiModelProperty("退款银行账户")
        private String refundAccount;

        @ApiModelProperty("退款银行账户")
        private String refundName;

        @ApiModelProperty("收款银行名字")
        private String collectBankName;

        @ApiModelProperty("收款银行账户")
        private String collectAccount;

        @ApiModelProperty("收款人")
        private String collectName;
        @ApiModelProperty("退款方式")
        private Integer refundType;


    }


}
