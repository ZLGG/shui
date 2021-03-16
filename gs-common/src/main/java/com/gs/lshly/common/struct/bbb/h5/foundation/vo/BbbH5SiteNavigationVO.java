package com.gs.lshly.common.struct.bbb.h5.foundation.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
* @author hyy
* @since 2020-11-04
*/
public abstract class BbbH5SiteNavigationVO implements Serializable {

    @Data
    @ApiModel("BbbH5SiteNavigationVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("导航名称")
        private String name;

        @ApiModelProperty("链接地址")
        private String url;

        @ApiModelProperty("热点图片")
        private String hotImageUrl;
    }
    
    @Data
    @ApiModel("BbbH5SiteNavigationVO.HomeVO")
    @Accessors(chain = true)
    public static class HomeVO implements Serializable{

        @ApiModelProperty("轮播图")
        private List<BannerVO> bannerList = new ArrayList<>();

        @ApiModelProperty("顶部链接")
        private List<TopLinkVO> topLinkList = new ArrayList<>();

        @ApiModelProperty("菜单导航")
        private List<MenuVO> menuList = new ArrayList<>();

    }

    @Data
    @ApiModel("BbbH5SiteNavigationVO.DetailVO")
    @Accessors(chain = true)
    public static class DetailVO implements Serializable{

        @ApiModelProperty("顶部链接")
        private List<TopLinkVO> topLinkList = new ArrayList<>();

        @ApiModelProperty("菜单导航")
        private List<MenuVO> menuList = new ArrayList<>();

    }

    @Data
    @ApiModel("BbbH5SiteNavigationVO.BannerVO")
    @Accessors(chain = true)
    public static class BannerVO implements Serializable{

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("文字")
        private String text;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;
    }


    @Data
    @ApiModel("BbbH5SiteNavigationVO.MenuVO")
    @Accessors(chain = true)
    public static class MenuVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("导航名称")
        private String name;

        @ApiModelProperty("链接地址")
        private String url;

        @ApiModelProperty("排序")
        private Integer idx;

    }

    @Data
    @ApiModel("BbbH5SiteNavigationVO.TopLinkVO")
    @Accessors(chain = true)
    public static class TopLinkVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("导航名称")
        private String name;

        @ApiModelProperty("链接地址")
        private String url;

        @ApiModelProperty("排序")
        private Integer idx;

    }

}
