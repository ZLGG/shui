package com.gs.lshly.common.struct.bbb.h5.foundation.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
* @author Starry
* @since 2020-10-06
*/
public abstract class BbbH5PicturesVO implements Serializable {


    @Data
    @ApiModel("BbcPicturesVO.DetailVO")
    public static class DetailVO implements Serializable {

        @ApiModelProperty("图库id")
        private String id;


        @ApiModelProperty("文件大小")
        private String size;


        @ApiModelProperty("图片高度")
        private Integer imgHeight;

        @ApiModelProperty("图片宽度")
        private Integer imgWeight;

        @ApiModelProperty("文件格式")
        private String imgType;


        @ApiModelProperty("图片名字")
        private String imageName;


        @ApiModelProperty("图片路径")
        private String imageUrl;
    }
}
