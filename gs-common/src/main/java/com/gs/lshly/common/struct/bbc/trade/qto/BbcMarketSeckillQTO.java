package com.gs.lshly.common.struct.bbc.trade.qto;

import java.io.Serializable;

import com.gs.lshly.common.struct.BaseQTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年4月15日 上午10:57:30
 */
public abstract class BbcMarketSeckillQTO implements Serializable {

    @Data
    @ApiModel("BbcMarketActivityQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    	@ApiModelProperty("活动ID")
        private String id;
    }
    
}
