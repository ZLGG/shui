package com.gs.lshly.common.struct.bbc.trade.qto;

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
public abstract class BbcMarketActivityQTO implements Serializable {

    @Data
    @ApiModel("BbcMarketActivityQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("店铺ID")
        private String shopId;
    }
    @Data
    @ApiModel("BbcMarketActivityQTO.QTO")
    @Accessors(chain = true)
    public static class IdQTO extends BaseQTO {
        @ApiModelProperty("活动ID")
        private String id;

    }
    
    
    @Data
    @ApiModel("BbcMarketActivityQTO.SeckillHomeQTO")
    @Accessors(chain = true)
    public static class SeckillHomeQTO extends BaseQTO {
        @ApiModelProperty(value= "秒杀活动ID",required = false)
        private String id;
    }
}
