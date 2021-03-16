package com.gs.lshly.common.struct.bbb.h5.pages.vo;

import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteAdvertVO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteBannerVO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteVideoVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
* @author Starry
* @since 2020-10-16
*/
public abstract class BbbH5HlyVO implements Serializable {

    @Data
    @ApiModel("BbbH5HlyVO.TopComplexVO")
    @Accessors(chain = true)
    public static class TopComplexVO implements Serializable{

        @ApiModelProperty("轮播图图片数组")
        private List<BbbH5SiteBannerVO.ListVO> bannerList = new ArrayList<>();

        @ApiModelProperty("广告图列表")
        private List<BbbH5SiteAdvertVO.SubjectListVO> advertList = new ArrayList<>();

        @ApiModelProperty("视频列表")
        private List<BbbH5SiteVideoVO.ListVO> videoList = new ArrayList<>();
    }

}
