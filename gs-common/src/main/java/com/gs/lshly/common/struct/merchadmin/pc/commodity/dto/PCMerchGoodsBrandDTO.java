package com.gs.lshly.common.struct.merchadmin.pc.commodity.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author Starry
 * @Date 20:05 2020/10/19
 */
public abstract class PCMerchGoodsBrandDTO  implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsBrandDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        @ApiModelProperty(value = "商品类别id")
        private String id;
    }
}
