package com.gs.lshly.common.struct.merchadmin.pc.trade.qto;

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
* @author oy
* @since 2020-11-20
*/
public abstract class PCMerchTradeCancelQTO implements Serializable {

    @Data
    @ApiModel("PCMerchTradeCancelQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("订单ID")
        private String tradeId;

        @ApiModelProperty("交易编号")
        private String tradeCode;

        @ApiModelProperty("提交申请:10,商家驳回:20,商家确认:30,退款:40,完成:50")
        private Integer cancelState;

        @ApiModelProperty("申请开始时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime startTime;
        @ApiModelProperty("申请结束时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime endTime;

        @ApiModelProperty("来源类型:10:2C,20:2B,30:POS")
        private Integer sourceType;


    }
}
