package com.gs.lshly.common.struct.bbc.foundation.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author 陈奇
* @since 2020-10-14
*/
public abstract class BBCHomePageBannerVO implements Serializable {

    @Data
    @ApiModel("BBC.SiteBannerVO.ListVO")
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

        @ApiModelProperty("播放速度")
        private Float speed;

        @ApiModelProperty("排序")
        private Integer idx;

    }

    @Data
    @ApiModel("BBC.SiteBannerVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
