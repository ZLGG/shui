package com.gs.lshly.common.struct.merchadmin.pc.commodity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author Starry
 * @Date 20:06 2020/10/19
 */
public abstract class PCMerchGoodsBrandVO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsBrandVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable {
        @ApiModelProperty(value = "品牌id")
        private String id;

        @ApiModelProperty(value = "品牌名称")
        private String brandName;

        @ApiModelProperty(value = "品牌别名")
        private String brandAlias;

        @ApiModelProperty(value = "品牌logo")
        private String brandLogo;

        @ApiModelProperty(value = "顺序")
        private Integer idx;
    }


    @Data
    @ApiModel("PCMerchGoodsBrandVO.innerBrandVO")
    @Accessors(chain = true)
    public static class innerBrandVO implements Serializable {
        @ApiModelProperty(value = "品牌id")
        private String id;

        @ApiModelProperty(value = "品牌名称")
        private String brandName;

    }
}
