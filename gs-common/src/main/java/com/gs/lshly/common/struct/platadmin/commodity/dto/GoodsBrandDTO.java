package com.gs.lshly.common.struct.platadmin.commodity.dto;


import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Hasee
 * @since 2020/09/17
 */
public abstract class GoodsBrandDTO implements Serializable  {

    @Data
    @ApiModel("GoodsBrandDTO.ETO")
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "品牌id", hidden = true)
        private String id;

        @ApiModelProperty(value = "品牌名称")
        private String brandName;

        @ApiModelProperty(value = "品牌别名")
        private String brandAlias;

        @ApiModelProperty(value = "品牌logo")
        private String brandLogo;

        @ApiModelProperty(value = "顺序")
        private Integer idx;

        @ApiModelProperty("类目id列表")
        private List<String> categoryIds = new ArrayList<>();

    }

    @Data
    @AllArgsConstructor
    @ApiModel("GoodsBrandDTO.IdDTO")
    @Accessors(chain = true)
    public static class IdDTO extends BaseDTO {
        @ApiModelProperty("品牌id")
        private String id;
    }

    @Data
    @ApiModel("GoodsBrandDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {
        @ApiModelProperty("品牌id列表")
        private List<String> idList;
    }

    @Data
    @AllArgsConstructor
    @ApiModel("GoodsBrandDTO.BrandNameDTO")
    public static class BrandNameDTO extends BaseDTO {
        @ApiModelProperty("品牌名称")
        private String brandName;
    }

}
