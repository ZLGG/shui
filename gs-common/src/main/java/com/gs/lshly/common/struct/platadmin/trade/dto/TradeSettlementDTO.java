package com.gs.lshly.common.struct.platadmin.trade.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.stock.dto.BbcStockDeliveryDTO;
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
* @author zst
* @since 2020-11-30
*/
public abstract class TradeSettlementDTO implements Serializable {

    @Data
    @ApiModel("TradeSettlementDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "结算汇总ID",hidden = true)
        private String id;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("所属商家")
        private String shopName;

        @ApiModelProperty("账单编号")
        private String tradeCode;

        @ApiModelProperty("订单数量")
        private Integer quantity;

        @ApiModelProperty("销售价")
        private BigDecimal salePrice;

        @ApiModelProperty("优惠卷")
        private BigDecimal coupons;

        @ApiModelProperty("运费金额")
        private BigDecimal deliveryAmount;

        @ApiModelProperty("退款金额")
        private BigDecimal tradeAmount;

        @ApiModelProperty("佣金")
        private BigDecimal commission;

        @ApiModelProperty("结算金额")
        private BigDecimal settlementAmount;

        @ApiModelProperty("账单开始时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime billStartTime;

        @ApiModelProperty("账单结束时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime billEndTime;

        @ApiModelProperty("结算时间")
        private LocalDateTime settlementTime;

        @ApiModelProperty("结算状态")
        private Integer settlementState;

    }

    @Data
    @ApiModel("TradeSettlementDTO.IdOfDTO")
    @AllArgsConstructor
    public static class IdOfDTO extends BaseDTO {

        @ApiModelProperty(value = "结算汇总ID")
        private String id;


    }


    @Data
    @ApiModel("TradeSettlementDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "结算汇总ID")
        private String id;

        @ApiModelProperty(value = "商家id")
        private String shopId;

        @ApiModelProperty("账单开始时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime billStartTime;

        @ApiModelProperty("账单结束时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime billEndTime;

    }

    @Data
    @ApiModel("TradeSettlementDTO.DeliveryAmountDTO")
    @Accessors(chain = true)
    public static class DeliveryAmountDTO extends BaseDTO {

        @ApiModelProperty(value = "店铺id")
        private String shopId;

    }


}
