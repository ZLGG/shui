package com.gs.lshly.common.struct.platadmin.commodity.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author Starry
 * @Date 11:10 2020/9/25
 */
public abstract class GoodsSpecDictionaryItemDTO implements Serializable {

    @Data
    public static class ETO extends BaseDTO{

        @ApiModelProperty(value = "规格值id", hidden = true)
        private String id;

        @ApiModelProperty(value = "规格值名称")
        private String specValue;

        @ApiModelProperty(value = "规格值图片")
        private String specImage;
    }

    @Data
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO{
        @ApiModelProperty(value = "规格值id")
        private String id;
    }

}
