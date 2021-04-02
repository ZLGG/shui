package com.gs.lshly.common.struct.bbc.merchant.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-13
*/
public abstract class BbcShopVO implements Serializable {

    @Data
    @ApiModel("BbcShopVO.ListVO")
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
    @ApiModel("BbcShopVO.ScopeListVO")
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
    @ApiModel("BbcShopVO.ShopIdName")
    public static class ShopIdName implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("店铺名称")
        private String shopName;
    }

    @Data
    @ApiModel("BbcShopVO.ShopNavigationIdName")
    @NoArgsConstructor
    public static class ShopNavigationIdName implements Serializable{

        @ApiModelProperty("店铺一级分类ID")
        private String id;

        @ApiModelProperty("店铺一级分类名")
        private String navName;

        @ApiModelProperty("店铺二级分类数组")
        List<BbcShopVO.ShopNavigationChildIdName> navigationList;
    }


    @Data
    @ApiModel("BbcShopVO.IdNameItem")
    public static class ShopNavigationChildIdName implements Serializable{

        @ApiModelProperty("店铺分类ID")
        private String id;

        @ApiModelProperty("店铺分类名")
        private String navName;
    }

    @Data
    @ApiModel("BbcShopVO.DetailVO")
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

        @ApiModelProperty("POS店铺ID")
        private String posShopId;

    }

    @Data
    @ApiModel("BbcShopVO.ComplexVO")
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

        @ApiModelProperty("是否收藏[0=否 1=是]")
        private Integer favoriteState;

        @ApiModelProperty("店铺文本导航")
        private List<BbcShopNavigationMenuVO.TextMenuVO> textMenuList;

        @ApiModelProperty("店铺轮播图")
        private List<BbcShopBannerVO.ListVO> bannerList;

        @ApiModelProperty("店铺菜单导航")
        private List<BbcShopNavigationMenuVO.MenuListVO> menuList;

        @ApiModelProperty("店铺楼层")
        private List<BbcShopFloorVO.ListVO> floorList;

    }

    @Data
    @ApiModel("BbcShopVO.InnerDetailVO")
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

        @ApiModelProperty("店主姓名")
        private String shopManName;

        @ApiModelProperty("店铺联系人手机号")
        private String shopManPhone;

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
            String shopProvinceTextO=StringUtils.isEmpty(shopProvinceText)?"":shopProvinceText;
            String shopCityTextO=StringUtils.isEmpty(shopCityText)?"":shopCityText;
            String shopCountyTextO=StringUtils.isEmpty(shopCountyText)?"":shopCountyText;
            return shopProvinceTextO + shopCityTextO+ shopCountyTextO + shopAddress;
        }

    }

    @Data
    @ApiModel("BbcShopVO.ShopNavVO")
    public static class ShopNavVO implements Serializable{

        @ApiModelProperty("店铺一级分类ID")
        private String id;

        @ApiModelProperty("店铺一级分类名称")
        private String navName;

        @ApiModelProperty("店铺二级分类数组")
        private List<ShopChildNavVO> childNavList = new ArrayList<>();

    }

    @Data
    @ApiModel("BbcShopVO.ShopChildNavVO")
    public static class ShopChildNavVO implements Serializable{

        @ApiModelProperty("店铺二级分类ID")
        private String id;

        @ApiModelProperty("店铺二级分类名称")
        private String navName;
    }
    @Data
    @ApiModel("BbcShopVO.isCity")
    public static class isCity implements Serializable{

        @ApiModelProperty("是否是同一城市[10=是 20=否]")
        private Integer isCity;

        @ApiModelProperty("距离")
        private double distance;
    }
}
