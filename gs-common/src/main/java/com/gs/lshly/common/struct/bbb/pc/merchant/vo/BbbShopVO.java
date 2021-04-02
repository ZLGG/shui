package com.gs.lshly.common.struct.bbb.pc.merchant.vo;
import com.gs.lshly.common.enums.TrueFalseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-13
*/
public abstract class BbbShopVO implements Serializable {

    @Data
    @ApiModel("BbbShopVO.ListVO")
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

        @ApiModelProperty("收藏状态[0=否 1=是]")
        private Integer favoriteState = TrueFalseEnum.否.getCode();

    }


    @Data
    @ApiModel("BbbShopVO.ScopeListVO")
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

        @ApiModelProperty("收藏状态[0=否 1=是]")
        private Integer favoriteState = TrueFalseEnum.否.getCode();

    }

    @Data
    @ApiModel("BbbShopVO.ShopIdName")
    public static class ShopIdName implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("店铺名称")
        private String shopName;
    }

    @Data
    @ApiModel("BbbShopVO.ShopNavigationIdName")
    @NoArgsConstructor
    public static class ShopNavigationIdName implements Serializable{

        @ApiModelProperty("店铺一级分类ID")
        private String id;

        @ApiModelProperty("店铺一级分类名")
        private String navName;

        @ApiModelProperty("店铺二级分类数组")
        List<BbbShopVO.ShopNavigationChildIdName> navigationList;
    }


    @Data
    @ApiModel("BbbShopVO.IdNameItem")
    public static class ShopNavigationChildIdName implements Serializable{

        @ApiModelProperty("店铺分类ID")
        private String id;

        @ApiModelProperty("店铺分类名")
        private String navName;
    }

    @Data
    @ApiModel("BbbShopVO.DetailVO")
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

        @ApiModelProperty("收藏状态[0=否 1=是]")
        private Integer favoriteState = TrueFalseEnum.否.getCode();

    }

    @Data
    @ApiModel("BbbShopVO.ShopScoreDetailVO")
    @Accessors(chain = true)
    public static class ShopScoreDetailVO implements Serializable {

        @ApiModelProperty("店铺ID")
        private String id;

        @ApiModelProperty("店铺Logo")
        private String shopLogo;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺描述")
        private String shopDesc;

        @ApiModelProperty("店铺联系人手机号")
        private String shopManPhone;

        @ApiModelProperty("店铺评分")
        private BigDecimal shopScore;

        @ApiModelProperty("商品评分")
        private BigDecimal goodsScore;

        @ApiModelProperty("商品评分人数")
        private Integer goodsScoreNum;

        @ApiModelProperty("服务评分")
        private BigDecimal serviceScore;

        @ApiModelProperty("配送评分")
        private BigDecimal deliveryScore;

        @ApiModelProperty("店铺收藏状态")
        private Integer favoriteState = TrueFalseEnum.否.getCode();
    }

    @Data
    @ApiModel("BbbShopVO.ComplexDetailVO")
    public static class ComplexDetailVO implements Serializable{

        @ApiModelProperty("店铺ID")
        private String id;

        @ApiModelProperty("POS门店ID")
        private String posShopId;

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
            return shopProvinceText + shopCityText+ shopCountyText + shopAddress;
        }

        @ApiModelProperty("收藏状态[0=否 1=是]")
        private Integer favoriteState = TrueFalseEnum.否.getCode();


    }

}
