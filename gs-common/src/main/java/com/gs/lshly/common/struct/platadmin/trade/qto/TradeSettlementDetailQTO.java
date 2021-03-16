package com.gs.lshly.common.struct.platadmin.trade.qto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
* @author zst
* @since 2020-12-01
*/
public abstract class TradeSettlementDetailQTO implements Serializable {

    @Data
    @ApiModel("TradeSettlementDetailQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }

    @Data
    @ApiModel("TradeSettlementDetailQTO.ListQTO")
    @Accessors(chain = true)
    public static class ListQTO extends BaseQTO {

        @ApiModelProperty("账单开始时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime billStartTime;

        @ApiModelProperty("账单结算时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime billEndTime;

        @ApiModelProperty("商家ID")
        private String shopId;

        @ApiModelProperty("结算类型[10普通结算 20售后结算]")
        private Integer settlementType;

        @ApiModelProperty("账单编号")
        private String tradeCode;
    }
}
