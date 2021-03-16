package com.gs.lshly.common.struct.platadmin.trade.qto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
* @author zst
* @since 2020-11-30
*/
public abstract class TradeSettlementQTO implements Serializable {

    @Data
    @ApiModel("TradeSettlementQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

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

        @ApiModelProperty("运费金额")
        private BigDecimal deliveryAmount;

        @ApiModelProperty("退款金额")
        private BigDecimal tradeAmount;

        @ApiModelProperty("佣金")
        private BigDecimal commission;

        @ApiModelProperty("结算金额")
        private BigDecimal settlementAmount;

        @ApiModelProperty("账单开始时间")
        private LocalDateTime billStartTime;

        @ApiModelProperty("账单结束时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime billEndTime;

        @ApiModelProperty("结算时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime settlementTime;

        @ApiModelProperty("结算状态[10 未结算 20已结算]")
        private Integer settlementState;

    }



    @Data
    @ApiModel("TradeSettlementQTO.settlementList")
    @Accessors(chain = true)
    public static class settlementList extends BaseQTO {


        @ApiModelProperty("账单开始时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime billStartTime;

        @ApiModelProperty("账单结算时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime billEndTime;

        @ApiModelProperty("商家id")
        private String shopId;

        @ApiModelProperty("结算状态")
        private Integer settlementState;
    }


    @Data
    @ApiModel("TradeSettlementQTO.PcQTO")
    @Accessors(chain = true)
    public static class PcQTO extends BaseQTO {

        @ApiModelProperty("账单开始时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime billStartTime;

        @ApiModelProperty("账单结算时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime billEndTime;

        @ApiModelProperty("结算状态[10 未结算 20已结算]")
        private Integer settlementState;
    }


    @Data
    @ApiModel("TradeSettlementQTO.SaleslQTO")
    @Accessors(chain = true)
    public static class SaleslQTO extends BaseQTO {

        @ApiModelProperty("开始时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime startTime;

        @ApiModelProperty("结束时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime endTime;

        @ApiModelProperty("选择查询时间")
        private Integer queryTimes;

        @ApiModelProperty("选择查询方式")
        private Integer queryType;
    }

    @Data
    @ApiModel("TradeSettlementQTO.DownloadExportQTO")
    @Accessors(chain = true)
    public static class DownloadExportQTO extends BaseQTO {

        @ApiModelProperty("开始时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime startTime;

        @ApiModelProperty("结束时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime endTime;

        @ApiModelProperty("账单编号")
        private String tradeCode;


    }


}
