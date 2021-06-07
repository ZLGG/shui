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
public abstract class BbcTradeRightsLogQTO implements Serializable {

    @Data
    @ApiModel("BbcTradeRightsLogQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {
        @ApiModelProperty("售后表id")
        private String rightsId;
    }
}
