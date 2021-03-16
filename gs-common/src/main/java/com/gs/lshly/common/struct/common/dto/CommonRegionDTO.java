package com.gs.lshly.common.struct.common.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author zst
* @since 2021-01-14
*/
public abstract class CommonRegionDTO implements Serializable {

    @Data
    @ApiModel("CommonRegionDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "上级地区")
        private String superiorName;

        @ApiModelProperty(value = "上级编号")
        private String superiorCode;

        @ApiModelProperty(value = "省名字")
        private String provinceName;

        @ApiModelProperty(value = "省编号")
        private String provinceCode;

        @ApiModelProperty(value = "市名字")
        private String cityName;

        @ApiModelProperty(value = "市编号")
        private String cityCode;

        @ApiModelProperty(value = "区名字")
        private String districtName;

        @ApiModelProperty(value = "区编号")
        private String districtCode;

        @ApiModelProperty(value = "层级")
        private Integer level;


    }

    @Data
    @ApiModel("CommonRegionDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        private String id;
    }


    @Data
    @ApiModel("CommonRegionDTO.EditRegion")
    @Accessors(chain = true)
    public static class EditRegion extends BaseDTO {

        @ApiModelProperty(value = "ID",hidden = true)
        private String id;

        @ApiModelProperty(value = "省名字")
        private String provinceName;

        @ApiModelProperty(value = "市名字")
        private String cityName;

        @ApiModelProperty(value = "区名字")
        private String districtName;

    }

}
