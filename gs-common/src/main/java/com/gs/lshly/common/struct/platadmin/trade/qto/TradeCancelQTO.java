package com.gs.lshly.common.struct.platadmin.trade.qto;

import com.gs.lshly.common.struct.BaseQTO;
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
* @since 2020-11-21
*/
public abstract class TradeCancelQTO implements Serializable {

    @Data
    @ApiModel("TradeCancelQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("来源[10=2C 20=2b]")
        private Integer sourceType;

        @ApiModelProperty("订单号(模糊查询)")
        private String tradeCode;

        @ApiModelProperty("会员名字")
        private String userName;
    }
    @Data
    @ApiModel("TradeCancelQTO.IdListQTO")
    public static class IdListQTO implements Serializable{

        @ApiModelProperty("订单ID列表")
        private List<String> idList;

    }
}
