package com.gs.lshly.common.struct.bbb.pc.commodity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Hasee
 * @since 2020/9/17
 */
public abstract class PCBbbGoodsBrandVO implements Serializable {

    @Data
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
    public static class DetailVO extends PCBbbGoodsBrandVO.ListVO {

    }
}
