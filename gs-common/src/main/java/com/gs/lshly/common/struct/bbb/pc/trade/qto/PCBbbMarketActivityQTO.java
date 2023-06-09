package com.gs.lshly.common.struct.bbb.pc.trade.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author zdf
* @since 2021-01-08
*/
public abstract class PCBbbMarketActivityQTO implements Serializable {

    @Data
    @ApiModel("PCBbbMarketActivityQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("店铺ID")
        private String shoId;

    }
    @Data
    @ApiModel("PCBbbMarketActivityQTO.QTO")
    @Accessors(chain = true)
    public static class IdQTO extends BaseQTO {
        @ApiModelProperty("活动ID")
        private String id;

    }
}
