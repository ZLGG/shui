package com.gs.lshly.common.struct.bbb.h5.trade.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author Starry
* @since 2020-12-23
*/
public abstract class BbbH5TradeComplaintQTO implements Serializable {

    @Data
    @ApiModel("BbbH5TradeComplaintQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }
}
