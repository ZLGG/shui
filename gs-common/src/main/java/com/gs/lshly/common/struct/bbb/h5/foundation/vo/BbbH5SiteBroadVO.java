package com.gs.lshly.common.struct.bbb.h5.foundation.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author hyy
* @since 2020-11-03
*/
public abstract class BbbH5SiteBroadVO implements Serializable {

    @Data
    @ApiModel("BbbH5SiteBroadVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("文本内容")
        private String text;

        @ApiModelProperty("跳转地址")
        private String linkUrl;

        @ApiModelProperty("排序")
        private Integer idx;
    }

    @Data
    @ApiModel("BbbH5SiteBroadVO.DetailsVO")
    @Accessors(chain = true)
    public static class DetailsVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("文本内容")
        private String text;

        @ApiModelProperty("跳转地址")
        private String linkUrl;

        @ApiModelProperty("排序")
        private Integer idx;

    }
}
