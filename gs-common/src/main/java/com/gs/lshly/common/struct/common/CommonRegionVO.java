package com.gs.lshly.common.struct.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xxfc
 * @date 17:38 2020/10/12
 */
public abstract class CommonRegionVO implements Serializable {

    @ApiModel("CommonRegionVO.ProvinceVO")
    @Data
    public static class ProvinceVO implements Serializable {

        @ApiModelProperty(value = "id",position = 4)
        private String deleteId;

        @ApiModelProperty(value = "省代码",position = 1)
        private String id;

        @ApiModelProperty(value = "省名称",position = 2)
        private String name;

        @ApiModelProperty(value = "市数组",position = 3)
        private List<CityVO> children = new ArrayList<>();

    }

    @ApiModel("CommonRegionVO.CityVO")
    @Data
    public static class CityVO implements Serializable {

        @ApiModelProperty(value = "id",position = 4)
        private String deleteId;

        @ApiModelProperty(value = "市代码",position = 1)
        private String id;

        @ApiModelProperty(value = "市名称",position = 2)
        private String name;

        @ApiModelProperty(value = "区县数组",position = 4)
        private List<CountyVO> children = new ArrayList<>();

    }

    @ApiModel("CommonRegionVO.CountyVO")
    @Data
    public static class CountyVO implements Serializable {

        @ApiModelProperty(value = "id",position = 3)
        private String deleteId;

        @ApiModelProperty(value = "区县代码",position = 1)
        private String id;

        @ApiModelProperty(value = "区县名称",position = 2)
        private String name;

    }


    @ApiModel("CommonRegionVO.ProvinceShortVO")
    @Data
    public static class ProvinceShortVO implements Serializable {

        @ApiModelProperty(value = "id",position = 4)
        private String deleteId;

        @ApiModelProperty(value = "省代码",position = 1)
        private String id;

        @ApiModelProperty(value = "省名称",position = 2)
        private String name;

        @ApiModelProperty(value = "市数组",position = 3)
        private List<CityShortVO> children = new ArrayList<>();

    }

    @ApiModel("CommonRegionVO.CityShortVO")
    @Data
    public static class CityShortVO implements Serializable {

        @ApiModelProperty(value = "id",position = 3)
        private String deleteId;

        @ApiModelProperty(value = "市代码",position = 1)
        private String id;

        @ApiModelProperty(value = "市名称",position = 2)
        private String name;
    }

}
