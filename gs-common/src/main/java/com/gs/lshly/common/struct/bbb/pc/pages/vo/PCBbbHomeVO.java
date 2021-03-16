package com.gs.lshly.common.struct.bbb.pc.pages.vo;

import com.gs.lshly.common.enums.TrueFalseEnum;
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
public abstract class PCBbbHomeVO implements Serializable {

    @Data
    @ApiModel("PCBbbHomeVO.FloorOrAdvertVO")
    @Accessors(chain = true)
    public static class FloorOrAdvertVO implements Serializable{

        @ApiModelProperty("楼层数组")
        private List<PCFloorVO> floorList = new ArrayList<>();

        @ApiModelProperty("组合广告")
        private List<PCAdvertVO> groupAdvertList = new ArrayList<>();

        @ApiModelProperty("单图广告")
        private PCAdvertVO advert;
    }

    @Data
    @ApiModel("PCBbbHomeVO.PCAdvertVO")
    @Accessors(chain = true)
    public static class PCAdvertVO implements Serializable {

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
    @ApiModel("PCBbbHomeVO.PCFloorVO")
    @Accessors(chain = true)
    public static class PCFloorVO implements Serializable {

        @ApiModelProperty("楼层ID")
        private String id;

        @ApiModelProperty("楼层名")
        private String name;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty(value = "楼层图标")
        private String icon;

        @ApiModelProperty("楼层左侧大图")
        private String leftImage;

        @ApiModelProperty("楼层菜单")
        private List<PCBbbHomeVO.PCFloorMenu> floorMenuList = new ArrayList<>();

        @ApiModelProperty("左侧大图底部链接")
        private List<PCBbbHomeVO.PCFloorLink> floorLinkList = new ArrayList<>();
    }

    @Data
    @ApiModel("PCBbbHomeVO.PCFloorMenu")
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
    @ApiModel("PCBbbHomeVO.PCFloorLink")
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
    @ApiModel("PCBbbHomeVO.PCFloorMenuGoods")
    @Accessors(chain = true)
    public static class PCFloorMenuGoods implements Serializable {

        @ApiModelProperty("商品id")
        private String goodsId;

        @ApiModelProperty("店铺id")
        private String shopId;

        @ApiModelProperty("商品默认图片")
        private String goodsImage;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品标题")
        private String goodsTitle;

        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;

        @ApiModelProperty("商品批发价")
        private BigDecimal wholesalePrice;

        @ApiModelProperty(value = "排序")
        private Integer idx;
    }

    @Data
    @ApiModel("PCBbbHomeVO.ShopSearchInfo")
    @Accessors(chain = true)
    public static class ShopSearchInfo implements Serializable {

        @ApiModelProperty(value = "店铺ID")
        private String id;

        @ApiModelProperty("店铺类型[10=品牌旗舰店 20=品牌专卖店 30=类目专营店 40=运营商自营 50=多品类通用型]")
        private Integer shopType;

        @ApiModelProperty("店铺Logo")
        private String shopLogo;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺描述")
        private String shopDesc;

        @ApiModelProperty("店铺地址")
        private String shopAddress;

        @ApiModelProperty("评分")
        private BigDecimal score;

        @ApiModelProperty("销量")
        private Integer sales;

        @ApiModelProperty("是否收藏[0=否 1=是]")
        private Integer favoriteState = TrueFalseEnum.否.getCode();


    }

}
