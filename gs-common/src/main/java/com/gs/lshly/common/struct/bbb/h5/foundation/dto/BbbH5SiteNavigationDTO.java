package com.gs.lshly.common.struct.bbb.h5.foundation.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author hyy
* @since 2020-11-04
*/
public abstract class BbbH5SiteNavigationDTO implements Serializable {

    @Data
    @ApiModel("BbcSiteNavigationDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("导航名称")
        private String name;

        @ApiModelProperty("链接地址")
        private String url;

        @ApiModelProperty("热点图片(顶部导航专属)")
        private String hotImageUrl;

        @ApiModelProperty("导航类型[10=顶部链接 20=菜单导航]")
        private Integer type;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("终端[10=2b 20=2c]")
        private Integer terminal;

        @ApiModelProperty("是否PC显示[10=是 20=否]")
        private Integer pcShow;

        @ApiModelProperty("专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]")
        private Integer subject;
    }

    @Data
    @ApiModel("BbcSiteNavigationDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
