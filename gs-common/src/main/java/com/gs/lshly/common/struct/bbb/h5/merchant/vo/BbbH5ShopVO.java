package com.gs.lshly.common.struct.bbb.h5.merchant.vo;
import com.gs.lshly.common.enums.TrueFalseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-13
*/
public abstract class BbbH5ShopVO implements Serializable {

    @Data
    @ApiModel("BbbH5ShopVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("店铺Logo")
        private String shopLogo;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺描述")
        private String shopDesc;

        @ApiModelProperty("店铺评分")
        private BigDecimal shopScore;

        @ApiModelProperty("店铺总销售量")
        private Integer shopSalesVolume ;

        @ApiModelProperty("经度")
        private BigDecimal shopLongitude;

        @ApiModelProperty("纬度")
        private BigDecimal shopLatitude;

    }


    @Data
    @ApiModel("BbbH5ShopVO.ScopeListVO")
    @Accessors(chain = true)
    public static class ScopeListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("店铺Logo")
        private String shopLogo;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺描述")
        private String shopDesc;

        @ApiModelProperty("店铺评分")
        private BigDecimal shopScore;

        @ApiModelProperty("店铺总销售量")
        private Integer shopSalesVolume ;

        @ApiModelProperty("距离")
        private String distance;

    }

    @Data
    @ApiModel("BbbH5ShopVO.ShopIdName")
    public static class ShopIdName implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("店铺名称")
        private String shopName;
    }

    @Data
    @ApiModel("BbbH5ShopVO.ShopNavigationIdName")
    @NoArgsConstructor
    public static class ShopNavigationIdName implements Serializable{

        @ApiModelProperty("店铺一级分类ID")
        private String id;

        @ApiModelProperty("店铺一级分类名")
        private String navName;

        @ApiModelProperty("店铺二级分类数组")
        List<BbbH5ShopVO.ShopNavigationChildIdName> navigationList;
    }


    @Data
    @ApiModel("BbbH5ShopVO.IdNameItem")
    public static class ShopNavigationChildIdName implements Serializable{

        @ApiModelProperty("店铺分类ID")
        private String id;

        @ApiModelProperty("店铺分类名")
        private String navName;
    }

    @Data
    @ApiModel("BbbH5ShopVO.DetailVO")
    public static class DetailVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("店铺Logo")
        private String shopLogo;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺描述")
        private String shopDesc;

        @ApiModelProperty("店铺评分")
        private BigDecimal shopScore;

    }

    @Data
    @ApiModel("BbbH5ShopVO.ComplexVO")
    public static class ComplexVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("店铺Logo")
        private String shopLogo;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺描述")
        private String shopDesc;

        @ApiModelProperty("店铺评分")
        private BigDecimal shopScore;

        @ApiModelProperty("收藏人数")
        private Integer countFavorite;

        @ApiModelProperty("是否收藏[0=否 1=是]")
        private Integer favoriteState  = TrueFalseEnum.否.getCode();

        @ApiModelProperty("店铺菜单导航")
        private List<BbbH5ShopNavigationMenuVO.MenuListVO> menuList = new ArrayList<>();

        @ApiModelProperty("店铺文本导航")
        private List<BbbH5ShopNavigationMenuVO.TextMenuVO> textMenuList = new ArrayList<>();

        @ApiModelProperty("店铺轮播图")
        private List<BbbH5ShopBannerVO.ListVO> bannerList = new ArrayList<>();

        @ApiModelProperty("店铺楼层")
        private List<BbbH5ShopFloorVO.ListVO> floorList = new ArrayList<>();

    }

    @Data
    @ApiModel("BbbH5ShopVO.InnerDetailVO")
    public static class InnerDetailVO implements Serializable{

        @ApiModelProperty("店铺ID")
        private String id;

        @ApiModelProperty("店铺Logo")
        private String shopLogo;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺描述")
        private String shopDesc;

        @ApiModelProperty("店铺评分")
        private BigDecimal shopScore;

        @ApiModelProperty("省")
        private String shopProvince;

        @ApiModelProperty("市")
        private String shopCity;

        @ApiModelProperty("县区")
        private String shopCounty;

        @ApiModelProperty("省名称")
        private String shopProvinceText;

        @ApiModelProperty("市名称")
        private String shopCityText;

        @ApiModelProperty("县区名称")
        private String shopCountyText;

        @ApiModelProperty("街道")
        private String shopStreet;

        @ApiModelProperty("经度")
        private BigDecimal shopLongitude;

        @ApiModelProperty("纬度")
        private BigDecimal shopLatitude;

        @ApiModelProperty("店铺地址")
        private String shopAddress;

        @ApiModelProperty("店铺状态")
        private Integer shopState;

        @ApiModelProperty("门店支持的配送类型[10=快递 20=自提 30=门店配送]")
        private List<Integer> deliveryStyleList;

        @ApiModelProperty("门店配送范围")
        private BigDecimal deliveryScope;

        @ApiModelProperty("店铺地址全路径")
        private String shopFullAddres;

        public String getShopFullAddres(){
            return shopProvinceText + shopCityText+ shopCountyText + shopAddress;
        }

    }
    @Data
    @ApiModel("BbbH5ShopVO.isCity")
    public static class isCity implements Serializable{

        @ApiModelProperty("是否是同一城市[10=是 20=否]")
        private Integer isCity;

        @ApiModelProperty("距离")
        private double distance;
    }

    @Data
    @ApiModel("BbbH5ShopVO.ShopServiceVO")
    @Accessors(chain = true)
    public static class ShopServiceVO implements Serializable{

        @ApiModelProperty(value = "QQ号")
        private String account;

        @ApiModelProperty(value = "热线电话")
        private String phone;

    }
    

}
