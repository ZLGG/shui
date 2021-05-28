package com.gs.lshly.common.struct.bbc.stock.dto;

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
 * @date 2021年5月27日 下午8:00:42
 */
@SuppressWarnings("serial")
public abstract class BbcStockDTO implements Serializable {
	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("跟据SKUID获取库存数量")
    @Accessors(chain = true)
    public static class SkuIdDTO extends BaseDTO {

        @ApiModelProperty(value = "skuId")
        private String skuId;

    }

}
