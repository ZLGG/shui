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
public abstract class SiteNavigationVO implements Serializable {


    @Data
    @ApiModel("SiteNavigationVO.H5ListVO")
    @Accessors(chain = true)
    public static class H5ListVO implements Serializable {

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("导航图片")
        private String hotImageUrl;

        @ApiModelProperty("导航名称")
        private String name;

        @ApiModelProperty("链接地址")
        private String url;

        @ApiModelProperty("排序")
        private Integer idx;


    }

    @Data
    @ApiModel("SiteNavigationVO.PCListVO")
    @Accessors(chain = true)
    public static class PCListVO implements Serializable {

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("导航图片")
        private String hotImageUrl;

        @ApiModelProperty("导航名称")
        private String name;

        @ApiModelProperty("链接地址")
        private String url;

        @ApiModelProperty("排序")
        private Integer idx;


    }
}
