package com.gs.lshly.common.struct.bbb.h5.trade.qto;

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
public abstract class BbbH5MarketActivityQTO implements Serializable {

    @Data
    @ApiModel("BbbH5MarketActivityQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("店铺ID")
        private String shopId;
    }
    @Data
    @ApiModel("BbbH5MarketActivityQTO.QTO")
    @Accessors(chain = true)
    public static class IdQTO extends BaseQTO {
        @ApiModelProperty("活动ID")
        private String id;

    }
}
