package com.gs.lshly.common.struct.platadmin.foundation.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author 大飞船
 * @since 2020-09-29
 */
public abstract class SiteFloorMenuVO implements Serializable {

    @Data
    @ApiModel("SiteFloorMenuVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable {

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("菜单名")
        private String menuName;

        @ApiModelProperty("排序")
        private Integer idx;
    }

    @Data
    @ApiModel("SiteFloorMenuVO.BListVO")
    @Accessors(chain = true)
    public static class BListVO implements Serializable {

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("菜单名")
        private String menuName;

        @ApiModelProperty("链接地址")
        private String jumpUrl;

        @ApiModelProperty("开窗类型[10=当前 20=新窗]")
        private Integer openType;

        @ApiModelProperty("排序")
        private Integer idx;
    }

}
