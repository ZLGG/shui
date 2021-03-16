package com.gs.lshly.common.struct.bbc.foundation.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* @author hyy
* @since 2020-11-13
*/
public abstract class BbcDataArticleCategoryVO implements Serializable {

    @Data
    @ApiModel("BbcDataArticleCategoryVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("一级栏目id")
        private String id;

        @ApiModelProperty("一级栏目名称")
        private String name;

        @ApiModelProperty("一级栏目图标")
        private String icon;

        @ApiModelProperty(value = "排序",hidden = true)
        @JsonIgnore
        private Integer idx;

        @ApiModelProperty("文章列表")
        private List<ArticleListVO> articleList = new ArrayList<>();
    }

    @Data
    @ApiModel("BbcDataArticleCategoryVO.CategoryChildVO")
    @Accessors(chain = true)
    public static class CategoryChildVO implements Serializable{

        @ApiModelProperty("二级栏目id")
        private String id;

        @ApiModelProperty("二级栏名称")
        private String name;

        @ApiModelProperty("二级栏目图标")
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

        @ApiModelProperty("发布时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
        private LocalDateTime cdate;

        @ApiModelProperty(value = "排序",hidden = true)
        @JsonIgnore
        private Integer idx;

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
