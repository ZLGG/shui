package com.gs.lshly.common.struct.platadmin.foundation.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈奇
 * @since 2020-09-30
 */
public abstract class SiteFloorVO implements Serializable {

    @Data
    @ApiModel("SiteFloorVO.H5ListVO")
    @Accessors(chain = true)
    public static class H5ListVO implements Serializable {

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("楼层名")
        private String name;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("最大商品显示数量")
        private Integer goodsMax;

        @ApiModelProperty("楼层广告图")
        private String topImage;

        @ApiModelProperty("图片跳转链接")
        private String jumpUrl;

        @ApiModelProperty("商品列表")
        private List<H5GoodsItem> goodsItemList;
    }

    @Data
    @ApiModel("SiteFloorVO.H5GoodsItem")
    @Accessors(chain = true)
    public static class H5GoodsItem implements Serializable {

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("商品名称")
        private String goodsName;
    }


    @Data
    @ApiModel("SiteFloorVO.PCListVO")
    @Accessors(chain = true)
    public static class PCListVO implements Serializable {

        @ApiModelProperty("楼层名")
        private String name;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty(value = "楼层图标")
        private String icon;

        @ApiModelProperty("楼层左侧大图")
        private String leftImage;

        @ApiModelProperty("楼层菜单")
        private List<PCFloorMenu> floorMenuList = new ArrayList<>();

        @ApiModelProperty("底部链接")
        private List<PCFloorLink> floorLinkList = new ArrayList<>();
    }

    @Data
    @ApiModel("SiteFloorVO.PCFloorMenu")
    @Accessors(chain = true)
    public static class PCFloorMenu implements Serializable {

        @ApiModelProperty(value = "楼层菜单ID",hidden = true)
        private String id;

        @ApiModelProperty("菜单名称")
        private String menuName;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty(value = "菜单商品数组")
        private List<PCFloorMenuGoods> menuGoodsList = new ArrayList<>();

    }

    @Data
    @ApiModel("SiteFloorVO.PCFloorLink")
    @Accessors(chain = true)
    public static class PCFloorLink implements Serializable {

        @ApiModelProperty("底部链接名称")
        private String menuName;

        @ApiModelProperty("底部链接地址")
        private String jumpUrl;

        @ApiModelProperty("排序")
        private Integer idx;

    }

    @Data
    @ApiModel("SiteFloorVO.PCFloorMenuGoods")
    @Accessors(chain = true)
    public static class PCFloorMenuGoods implements Serializable {

        @ApiModelProperty("商品Id")
        private String goodsId;

        @ApiModelProperty("商品图片")
        private String goodsImage;

        @ApiModelProperty("商品名字")
        private String goodsName;

        @ApiModelProperty("排序")
        private Integer idx;
    }
}
