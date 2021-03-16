package com.gs.lshly.common.struct.platadmin.trade.qto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.enums.TradePayStateEnum;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author oy
* @since 2020-11-16
*/
public abstract class TradePayQTO implements Serializable {


    @Data
    @ApiModel("TradePayQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {
        @ApiModelProperty("空为全部 10=在线支付 90=货到付款")
        private Integer state;

        @ApiModelProperty("支付单号")
        private Integer id;

        @ApiModelProperty("支付状态[10=待支付 20=待确认 30=驳回 40=已支付]")
        private Integer payState;

        @ApiModelProperty("支付创建时间(介于大于等于)")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;

        @ApiModelProperty("支付创建时间(介于小于等于)")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdateLittleDate;

        @ApiModelProperty("支付创建时间状态[10=晚于 20=早于 30=是 40=介于]")
        private Integer cdateState;

    }
    @Data
    @ApiModel("TradePayQTO.RelationQTO")
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RelationQTO extends BaseQTO {
        @ApiModelProperty("订单号")
        private String tradeId;

        @ApiModelProperty("支付单号")
        private String tradePayId;

        @ApiModelProperty("支付状态[10=待支付 20=待确认 30=驳回 40=已支付]")
        private Integer payState;

        @ApiModelProperty("支付开始时间(介于大于等于)")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime payStartDate;

        @ApiModelProperty("支付开始时间(介于小于等于)")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime payStartLittleDate;

        @ApiModelProperty("支付开始时间状态[10=晚于 20=早于 30=是 40=介于]")
        private Integer payStartDateState;

        @ApiModelProperty("支付完成时间(介于大于等于)")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime payFinishDate;

        @ApiModelProperty("支付完成时间(介于小于等于)")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime payFinishLittleDate;


        @ApiModelProperty("支付完成时间状态[10=晚于 20=早于 30=是 40=介于]")
        private Integer payFinishDateState;

        @ApiModelProperty("最后修改时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime udate;

        @ApiModelProperty("最后修改时间状态[10=大于 20=等于 30=小于]")
        private Integer udateState;


    }
}
