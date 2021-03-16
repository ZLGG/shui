package com.gs.lshly.common.struct.merchadmin.pc.commodity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author Starry
 * @Date 20:34 2020/10/19
 */
public abstract class PCMerchGoodsSpecDictionaryItemVO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsSpecDictionaryItemVO.ListVO")
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
