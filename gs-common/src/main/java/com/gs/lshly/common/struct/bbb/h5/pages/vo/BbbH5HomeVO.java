package com.gs.lshly.common.struct.bbb.h5.pages.vo;
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
public abstract class BbbH5HomeVO implements Serializable {

    @Data
    @ApiModel("BbbH5HomeVO.TopComplexVO")
    @Accessors(chain = true)
    public static class TopComplexVO implements Serializable{

        @ApiModelProperty("楼层数组")
        private List<FloorVO> floorList = new ArrayList<>();

        @ApiModelProperty("组合广告")
        private List<AdvertVO> groupAdvertList = new ArrayList<>();

        @ApiModelProperty("单图广告")
        private AdvertVO advert;
    }

    @Data
    @ApiModel("BbbH5HomeVO.AdvertVO")
    @Accessors(chain = true)
    public static class AdvertVO implements Serializable {

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;

        @ApiModelProperty("文字")
        private String text;

        @ApiModelProperty("排序")
        private Integer idx;
    }

    @Data
    @ApiModel("BbbH5HomeVO.FloorVO")
    @Accessors(chain = true)
    public static class FloorVO implements Serializable {

        @ApiModelProperty("楼层ID")
        private String id;

        @ApiModelProperty("楼层名")
        private String name;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty(value = "楼层图标")
        private String icon;
    }


}
