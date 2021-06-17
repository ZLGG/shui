package com.gs.lshly.common.struct.bbc.trade.dto;

import java.io.Serializable;

import com.gs.lshly.common.struct.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年4月15日 上午10:51:19
 */
@SuppressWarnings("serial")
public abstract class BbcMarketSeckillDTO implements Serializable {
	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel(value = "DTO")
    @Accessors(chain = true)
    public static class DTO  extends BaseDTO implements Serializable {

        @ApiModelProperty(value = "秒杀ID")
        private String id;

    }
	@EqualsAndHashCode(callSuper=false)
	@Data
    @ApiModel(value = "SeckillSkuDTO")
    @Accessors(chain = true)
    public static class SeckillSkuDTO  extends BaseDTO implements Serializable {

        @ApiModelProperty(value = "秒杀时间段ID")
        private String timeQuantumId;
        
        @ApiModelProperty(value = "商品ID")
        private String goodsId;
        
        @ApiModelProperty(value = "SkuID")
        private String skuId;

    }


}
