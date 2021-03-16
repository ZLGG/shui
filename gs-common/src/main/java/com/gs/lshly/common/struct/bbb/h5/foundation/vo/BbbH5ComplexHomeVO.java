package com.gs.lshly.common.struct.bbb.h5.foundation.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
* @author hyy
* @since 2020-11-04
*/
public abstract class BbbH5ComplexHomeVO implements Serializable {

    @Data
    @ApiModel("BbbH5ComplexHomeVO.TopComplexVO")
    @Accessors(chain = true)
    public static class TopComplexVO implements Serializable{

        @ApiModelProperty("站点导航列表")
        private List<BbbH5SiteNavigationVO.ListVO> navigationList;

        @ApiModelProperty("轮播图列表")
        private List<BbbH5SiteBannerVO.ListVO> bannerList;

        @ApiModelProperty("首页视频")
        private List<BbbH5SiteVideoVO.ListVO> videoList;

        @ApiModelProperty("楼层列表")
        private List<BbbH5SiteFloorVO.ListVO> floorList;
    }
}
