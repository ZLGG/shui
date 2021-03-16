package com.gs.lshly.common.struct.bbb.h5.pages.vo;

import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteBannerVO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteFloorVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
* @author Starry
* @since 2020-10-16
*/
public abstract class BbbH5WelfareVO implements Serializable {

    @Data
    @ApiModel("BbbH5WelfareVO.TopComplexVO")
    @Accessors(chain = true)
    public static class TopComplexVO implements Serializable{

        @ApiModelProperty("轮播图图片数组")
        List<BbbH5SiteBannerVO.ListVO> bannerList;

        @ApiModelProperty("楼层数组")
        List<BbbH5SiteFloorVO.ListVO> floorList;

    }

    @Data
    @ApiModel("BbbH5WelfareVO.BannerVO")
    @Accessors(chain = true)
    public static class BannerVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("文字")
        private String text;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;

        @ApiModelProperty("排序")
        private Integer idx;
    }

    @Data
    @ApiModel("BbbH5WelfareVO.FloorVO")
    @Accessors(chain = true)
    public static class FloorVO implements Serializable{

        @ApiModelProperty("楼层ID")
        private String id;

        @ApiModelProperty("楼层名称")
        private String floorName;

        @ApiModelProperty("楼层最大商品数量")
        private Integer goodsMax;

    }

    @Data
    @ApiModel("BbbH5WelfareVO.FloorGoodVO")
    @Accessors(chain = true)
    public static class FloorGoodVO implements Serializable{

        @ApiModelProperty(value = "楼层ID")
        private String floorId;

        @ApiModelProperty(value = "商品ID")
        private String goodsId;

        @ApiModelProperty(value = "楼层的商品")
        private PageData<BbbH5GoodsInfoVO.HomeInnerServiceVO> goodsInfo;

    }
}
