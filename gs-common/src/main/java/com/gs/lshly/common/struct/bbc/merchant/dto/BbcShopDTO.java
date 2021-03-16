package com.gs.lshly.common.struct.bbc.merchant.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-13
*/
public abstract class BbcShopDTO implements Serializable {

    @Data
    @ApiModel("BBC.CommonShopDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("pos机id")
        private String posId;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺描述")
        private String shopDescribe;

        @ApiModelProperty("店铺商家类型[10=2b 20=2c]")
        private Integer shopMerchantType;

        @ApiModelProperty("店铺类型[10=品牌旗舰店 20=品牌专卖店 30=类目专营店 40=运营商自营 50=多品类通用型]")
        private Integer shopType;

        @ApiModelProperty("店铺状态[10=营业 20=关闭]")
        private Integer shopState;

        @ApiModelProperty("省")
        private String province;

        @ApiModelProperty("市")
        private String city;

        @ApiModelProperty("县")
        private String county;

        @ApiModelProperty("街道")
        private String street;

        @ApiModelProperty("详细地址")
        private String realAddress;

        @ApiModelProperty("经度")
        private Float lgt;

        @ApiModelProperty("纬度")
        private Float lat;

        @ApiModelProperty("商家ID")
        private String merchantId;
    }

    @Data
    @ApiModel("BBC.CommonShopDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "店铺ID")
        private String id;
    }

    @Data
    @ApiModel("CommonShopDTO.MerchantIdDTO")
    @AllArgsConstructor
    public static class MerchantIdDTO extends BaseDTO {
        @ApiModelProperty(value = "商家ID")
        private String id;
    }


    @Data
    @ApiModel("BBC.CommonShopDTO.IdListDTO")
    @AllArgsConstructor
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty(value = "店铺ID数组")
        private List<String> idList;
    }

    @Data
    @ApiModel("BbcShopDTO.isCity")
    @AllArgsConstructor
    public static class isCity extends BaseDTO {

        @ApiModelProperty(value = "店铺ID")
        private String id;

        @ApiModelProperty(value = "用户经度")
        private BigDecimal lng;

        @ApiModelProperty(value = "用户纬度")
        private BigDecimal lat;
    }


}
