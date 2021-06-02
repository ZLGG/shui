package com.gs.lshly.common.struct.platadmin.trade.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 
 *
 * 
 * @author hanly
 * @date 2021年6月2日 上午10:44:13
 */
@SuppressWarnings("serial")
public abstract class MarketPtSeckillTimeQuantumQTO implements Serializable {

    @Data
    @EqualsAndHashCode(callSuper=false)
    @ApiModel("MarketPtSeckillTimeQuantumQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {
    }

    @Data
    @ApiModel("MarketPtSeckillTimeQuantumQTO.IdQTO")
    @Accessors(chain = true)
    public static class IdQTO implements Serializable {
        @ApiModelProperty("时间段编号")
        private Integer id;
    }
}
