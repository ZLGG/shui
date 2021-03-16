package com.gs.lshly.common.struct.platadmin.stock.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhou
 * @date 17:38 2020/10/12
 */
public abstract class RegionVO implements Serializable {

    @ApiModel("RegionVO.ListVO")
    @Data
    public static class ListVO implements Serializable {

        @ApiModelProperty("ID")
        private String id;

        @ApiModelProperty("父ID")
        private String parentId;

        @ApiModelProperty("地区名")
        private String name;

        @ApiModelProperty("省")
        private String province;

        @ApiModelProperty("市")
        private String city;

        @ApiModelProperty("区")
        private String district;

        @ApiModelProperty("层级")
        private Integer level;
    }
}
