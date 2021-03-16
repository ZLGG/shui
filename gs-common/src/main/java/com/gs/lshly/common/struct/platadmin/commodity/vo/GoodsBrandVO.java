package com.gs.lshly.common.struct.platadmin.commodity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hasee
 * @since 2020/9/17
 */
public abstract class GoodsBrandVO implements Serializable {

    @Data
    @Accessors(chain = true)
    @ApiModel("GoodsBrandVO.ListVO")
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

        @ApiModelProperty("与品牌绑定的类目id列表")
        private List<String> categoryIds = new ArrayList<>();
    }

    @Data
    @ApiModel("GoodsBrandVO.DetailVO")
    public static class DetailVO extends ListVO {


    }

    @Data
    @ApiModel("GoodsBrandVO.BrandIdVO")
    public static class BrandIdVO implements Serializable {
        @ApiModelProperty(value = "品牌id")
        private String id;
    }
}
