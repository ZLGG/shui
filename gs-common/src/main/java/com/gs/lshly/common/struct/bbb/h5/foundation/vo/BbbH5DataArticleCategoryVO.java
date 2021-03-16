package com.gs.lshly.common.struct.bbb.h5.foundation.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
* @author hyy
* @since 2020-11-13
*/
public abstract class BbbH5DataArticleCategoryVO implements Serializable {

    @Data
    @ApiModel("BbbH5DataArticleCategoryVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("一级栏目id")
        private String id;

        @ApiModelProperty("一级栏目名称")
        private String name;

        @ApiModelProperty("一级栏目图标")
        private String icon;

        @ApiModelProperty("文章列表")
        private List<ArticleListVO> articleList = new ArrayList<>();

    }

    @Data
    @ApiModel("BbcDataArticleCategoryVO.ArticleListVO")
    @Accessors(chain = true)
    public static class ArticleListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("标题")
        private String title;

        @ApiModelProperty(value = "categoryId",hidden = true)
        private String categoryId;

    }

    @Data
    @ApiModel("BbcDataArticleCategoryVO.SearchListVO")
    public static class SearchListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("标题")
        private String title;

    }

    @Data
    @ApiModel("BbcDataArticleCategoryVO.DetailsVO")
    public static class DetailsVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("标题")
        private String title;

        @ApiModelProperty("内容")
        private String content;

    }
}
