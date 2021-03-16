package com.gs.lshly.common.struct.platadmin.commodity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author Starry
 * @Date 11:11 2020/9/25
 */
public abstract class GoodsSpecDictionaryItemVO implements Serializable {

    @Data
    @Accessors(chain = true)
    public static class ListVO implements Serializable{
        @ApiModelProperty(value = "规格值id")
        private String id;

        @ApiModelProperty(value = "规格值id")
        private String specId;

        @ApiModelProperty(value = "规格值名称")
        private String specValue;

        @ApiModelProperty(value = "规格值图片")
        private String specImage;
    }
}
