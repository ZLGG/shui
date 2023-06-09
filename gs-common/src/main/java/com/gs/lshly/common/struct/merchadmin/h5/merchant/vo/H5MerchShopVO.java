package com.gs.lshly.common.struct.merchadmin.h5.merchant.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
/**
* @author zst
* @since 2021-01-20
*/
public abstract class H5MerchShopVO implements Serializable {

    @Data
    @ApiModel("H5MerchShopVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("店铺Logo")
        private String shopLogo;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺描述")
        private String shopDescribe;

        @ApiModelProperty("邀请码")
        private String shareCode;

    }

    @Data
    @ApiModel("H5MerchShopVO.DetailVO")
    public static class DetailVO implements Serializable {

        @ApiModelProperty(value = "id")
        private String id;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺Logo")
        private String shopLogo;

        @ApiModelProperty("店铺描述")
        private String shopDescribe;

        @ApiModelProperty("店铺类型[10=品牌旗舰店 20=品牌专卖店 30=类目专营店 40=运营商自营 50=多品类通用型]")
        private Integer shopType;

        @ApiModelProperty("店铺联系人手机号")
        private String shopManPhone;

        @ApiModelProperty("省名称")
        private String provinceText;

        @ApiModelProperty("市名称")
        private String cityText;

        @ApiModelProperty("县名称")
        private String countyText;

        @ApiModelProperty("详细地址")
        private String realAddress;

        @ApiModelProperty("经度")
        private BigDecimal shopLongitude;

        @ApiModelProperty("纬度")
        private BigDecimal shopLatitude;

        @ApiModelProperty("是否开启地图导航")
        private Integer isFixedMap;

        @ApiModelProperty("邀请码")
        private String shareCode;

        @ApiModelProperty("配送距离")
        private Integer meters;

    }


    @Data
    @ApiModel("H5MerchShopVO.ShopSimpleVO")
    public static class ShopSimpleVO implements Serializable {

        @ApiModelProperty(value = "id")
        private String id;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺Log")
        private String shopLogo;

        @ApiModelProperty("店铺描述")
        private String shopDesc;

        @ApiModelProperty("店铺类型[10=品牌旗舰店 20=品牌专卖店 30=类目专营店 40=运营商自营 50=多品类通用型]")
        private Integer shopType;

        @ApiModelProperty("邀请码")
        private String shareCode;

    }

    @Data
    @ApiModel("H5MerchShopVO.ChangeShopVO")
    public static class ChangeShopVO implements Serializable {

        @ApiModelProperty(value = "店铺ID")
        private String id;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺类型[10=品牌旗舰店 20=品牌专卖店 30=类目专营店 40=运营商自营 50=多品类通用型]")
        private Integer shopType;

        @ApiModelProperty("切换后的token")
        private String token;

        @ApiModelProperty("邀请码")
        private String shareCode;

    }
}
