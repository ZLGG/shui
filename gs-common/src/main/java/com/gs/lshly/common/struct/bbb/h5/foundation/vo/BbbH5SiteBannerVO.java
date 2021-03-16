package com.gs.lshly.common.struct.bbb.h5.foundation.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author hyy
* @since 2020-11-04
*/
public abstract class BbbH5SiteBannerVO implements Serializable {

    @Data
    @ApiModel("BbcSiteBannerVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("文字")
        private String text;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;

        @ApiModelProperty("排序")
        private Integer idx;
    }
}
