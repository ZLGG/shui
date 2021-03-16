package com.gs.lshly.common.struct.bbc.foundation.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author 陈奇
* @since 2020-10-20
*/
public abstract class BbcHomePageVideoVO implements Serializable {

    @Data
    @ApiModel("SiteVideoVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("视频封面图片")
        private String imageUrl;

        @ApiModelProperty("视频链接")
        private String videoUrl;

    }

    @Data
    @ApiModel("SiteVideoVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
