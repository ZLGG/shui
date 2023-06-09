package com.gs.lshly.common.struct.bbb.pc.foundation.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author hyy
* @since 2020-11-03
*/
public abstract class BbbSiteAdvertVO implements Serializable {

    @Data
    @ApiModel("BbcSiteAdvertVO.CategoryListVO")
    @Accessors(chain = true)
    public static class CategoryListVO implements Serializable{

        @ApiModelProperty("商品类目ID")
        private String categoryId;

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;

        @ApiModelProperty("文字")
        private String text;
    }

    @Data
    @ApiModel("BbcSiteAdvertVO.SubjectListVO")
    @Accessors(chain = true)
    public static class SubjectListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;

        @ApiModelProperty("文字")
        private String text;

    }

    @Data
    @ApiModel("BbcSiteAdvertVO.InnerCategoryAdvertListVO")
    @Accessors(chain = true)
    public static class InnerCategoryAdvertListVO implements Serializable{

        @ApiModelProperty("商品类目ID")
        private String categoryId;

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;

        @ApiModelProperty("文字")
        private String text;
    }
}
