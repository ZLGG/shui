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
public abstract class BbbH5SiteBannerDTO implements Serializable {

    @Data
    @ApiModel("BbcSiteBannerDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
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

        @ApiModelProperty("是否PC显示[10=是 20=否]")
        private Integer pcShow;

        @ApiModelProperty("终端[10=2b 20=2c]")
        private Integer terminal;

        @ApiModelProperty("专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]")
        private Integer subject;

        @ApiModelProperty("是否是分类[1=是 0=否]")
        private Integer isClassify;

        @ApiModelProperty("产品类目id")
        private String productCategoryId;
    }

    @Data
    @ApiModel("BbcSiteBannerDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
