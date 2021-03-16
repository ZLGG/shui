package com.gs.lshly.common.struct.bbb.pc.foundation.vo;

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
public abstract class BbbArticleCategoryVO implements Serializable {


    @Data
    @ApiModel("BbbArticleCategoryVO.ArticleLinksVO")
    @Accessors(chain = true)
    public static class ArticleLinksVO implements Serializable{

        @ApiModelProperty("文章列表")
        private List<ListVO> articleList;

        @ApiModelProperty("链接列表")
        private List<LinksVO> linksList;
    }


    @Data
    @ApiModel("BbbArticleCategoryVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("一级栏目id")
        private String id;

        @ApiModelProperty("一级栏目名称")
        private String name;

        @ApiModelProperty(value = "排序",hidden = true)
        @JsonIgnore
        private Integer idx;

        @ApiModelProperty("一级栏目图标")
        private String icon;

        @ApiModelProperty("文章列表")
        private List<ArticleListVO> articleList = new ArrayList<>();
    }

    @Data
    @ApiModel("BbbArticleCategoryVO.ArticleListVO")
    @Accessors(chain = true)
    public static class ArticleListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty(value = "排序",hidden = true)
        @JsonIgnore
        private Integer idx;

        @ApiModelProperty("标题")
        private String title;

        @ApiModelProperty("创建时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;

    }

    @Data
    @ApiModel("BbbArticleCategoryVO.SearchListVO")
    public static class SearchListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("标题")
        private String title;

        @ApiModelProperty("创建时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;

    }

    @Data
    @ApiModel("BbbArticleCategoryVO.DetailsVO")
    public static class DetailsVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("标题")
        private String title;

        @ApiModelProperty("内容")
        private String content;

        @ApiModelProperty("创建时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;


    }

    @Data
    @ApiModel("BbbArticleCategoryVO.LinksVO")
    @Accessors(chain = true)
    public static class LinksVO implements Serializable{

        @ApiModelProperty("底部文章名")
        private String name;

        @ApiModelProperty("文章链接")
        private String articleUrl;

    }

    @Data
    @ApiModel("BbbArticleCategoryVO.HelpListVO")
    @Accessors(chain = true)
    public static class HelpListVO implements Serializable{

        @ApiModelProperty("一级栏目id")
        private String id;

        @ApiModelProperty("一级栏目名称")
        private String name;

        @ApiModelProperty(value = "排序",hidden = true)
        @JsonIgnore
        private Integer idx;

        @ApiModelProperty("一级栏目图标")
        private String icon;

        @ApiModelProperty("二级栏目数组")
        private List<HelpChildListVO> childList = new ArrayList<>();
    }

    @Data
    @ApiModel("BbbArticleCategoryVO.HelpChildListVO")
    @Accessors(chain = true)
    public static class HelpChildListVO implements Serializable{

        @ApiModelProperty("二级栏目id")
        private String id;

        @ApiModelProperty("二级栏目名称")
        private String name;

        @ApiModelProperty(value = "排序",hidden = true)
        @JsonIgnore
        private Integer idx;

        @ApiModelProperty("二级栏目图标")
        private String icon;

    }

}
