package com.gs.lshly.common.struct.bbb.h5.merchant.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Starry
 * @Date 11:10 2021/3/16
 */
public abstract class BbbH5ShopNavigationVO {


    @Data
    @ApiModel("BbbH5ShopNavigationVO.NavigationListVO")
    @Accessors(chain = true)
    public static class NavigationListVO implements Serializable{

        @ApiModelProperty(value = "id",position = 1)
        private String id;

        @ApiModelProperty(value = "分类名称",position = 2)
        private String navName;

        @ApiModelProperty(value = "排序",position = 3)
        private Integer idx;

        @ApiModelProperty(value = "图片地址",position = 4)
        private String imgUrl;

        @ApiModelProperty(value = "子分类节点数组",position = 5)
        List<NavigationChildVO> childList = new ArrayList<>();
    }

    @Data
    @ApiModel("BbbH5ShopNavigationVO.NavigationChildVO")
    public static class NavigationChildVO implements Serializable{

        @ApiModelProperty(value = "id",position = 1)
        private String id;

        @ApiModelProperty(value = "排序",position = 2)
        private Integer idx;

        @ApiModelProperty(value = "分类名称",position = 3)
        private String navName;

        @ApiModelProperty(value = "图片地址",position = 4)
        private String imgUrl;

    }
}
