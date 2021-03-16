package com.gs.lshly.common.struct.pos.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

public abstract class PosShopDTO {

    @Data
    @ApiModel("PosShopDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

    }
    @Data
    @ApiModel("PosShopDTO.ETO")
    @AllArgsConstructor
    public static class ETO extends BaseDTO {

        /**
         * 店铺ID
         */
        private String id;

        /**
         * 商家ID{如果没有，平台给值}
         */
        private String merchantId;

        /**
         * 主帐号ID{先给店铺生成一个商家和商家主帐号}
         */
        private String mainAccountId;

        /**
         * 店铺Logo
         */
        private String shopLogo;

        /**
         * 店铺名称
         */
        private String shopName;

        /**
         * 店铺描述
         */
        private String shopDesc;

        /**
         * 店主姓名
         */
        private String shopManName;

        /**
         * 店主身份证号
         */
        private String shopManIdcardNo;

        /**
         * 店主身份证复印件(正)
         */
        private String shopManIdcardFront;

        /**
         * 店主身份证复印件(反)
         */
        private String shopManIdcardBack;

        /**
         * 店铺类型[10=品牌旗舰店 20=品牌专卖店 30=类目专营店 40=运营商自营 50=多品类通用型]
         */
        private Integer shopType;

        /**
         * 店铺类型名称
         */
        private String shopTypeName;

        /**
         * 店铺商家类型[10=2b 20=2c]
         */
        private Integer terminal;

        /**
         * 店铺经营品牌ID
         */
        private String brandId;

        /**
         * 店铺经营品牌名称
         */
        private String brandName;

        /**
         * 品牌经营授权复印件
         */
        private String brandCert;

        /**
         * 省code
         */
        private String shopProvince;

        /**
         * 省名称
         */
        private String shopProvinceText;

        /**
         * 市code
         */
        private String shopCity;

        /**
         * 市名称
         */
        private String shopCityText;

        /**
         * 县区code
         */
        private String shopCounty;

        /**
         * 县区名称
         */
        private String shopCountyText;

        /**
         * 街道
         */
        private String shopStreet;

        /**
         * 经度
         */
        private BigDecimal shopLongitude;

        /**
         * 纬度
         */
        private BigDecimal shopLatitude;

        /**
         * 店铺地址
         */
        private String shopAddress;

        /**
         * 店铺联系人手机号
         */
        private String shopManPhone;

        /**
         * 店铺联系人邮箱
         */
        private String shopManEmail;

        /**
         * 商家来源[10=平台入驻 20=POS同步]
         */
        private Integer shopMerchantFrom;

    }

}
