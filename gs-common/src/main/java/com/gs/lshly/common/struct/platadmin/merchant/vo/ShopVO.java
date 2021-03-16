package com.gs.lshly.common.struct.platadmin.merchant.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public abstract class ShopVO implements Serializable {

    @Data
    @ApiModel("ShopVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty(value = "id",position = 1)
        private String id;

        @ApiModelProperty(value = "店铺名称",position = 2)
        private String shopName;

        @ApiModelProperty(value = "店铺描述",position = 3)
        private String shopDesc;

        @ApiModelProperty(value = "店铺商家类型[10=2b 20=2c]",position = 4)
        private Integer terminal;

        @ApiModelProperty(value = "店铺类型[10=品牌旗舰店 20=品牌专卖店 30=类目专营店 40=运营商自营 50=多品类通用型]",position = 5)
        private Integer shopType;

        @ApiModelProperty(value = "店铺状态[10=营业 20=关闭]",position = 6)
        private Integer shopState;

        @ApiModelProperty(value = "省ID",position = 7)
        private String shopProvince;

        @ApiModelProperty(value = "省名称",position = 7)
        private String shopProvinceText;

        @ApiModelProperty(value = "市ID",position = 8)
        private String shopCity;

        @ApiModelProperty(value = "市名称",position = 8)
        private String shopCityText;

        @ApiModelProperty(value = "县区ID",position = 9)
        private String shopCounty;

        @ApiModelProperty(value = "县区名称",position = 9)
        private String shopCountyText;

        @ApiModelProperty(value = "街道",position = 10)
        private String shopStreet;

        @ApiModelProperty(value = "详细地址",position = 11)
        private String shopAddress;

        @ApiModelProperty(value = "开店时间",position = 12)
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime openTime;

        @ApiModelProperty(value = "关店时间",position = 13)
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime closeTime;

        @ApiModelProperty(value = "关店原因",position = 14)
        private String closeWhy;

        @ApiModelProperty(value = "超级管理员帐号",position = 15)
        private String supperAccount;

        @ApiModelProperty(value = "商家来源[10=平台入驻 20=平台添加]",position = 16)
        private Integer shopMerchantFrom;

    }

    @Data
    @ApiModel("ShopVO.DetailVO")
    public static class DetailVO extends ListVO {

        @ApiModelProperty(value = "店铺商品类目",position = 16)
        private List<ShopGoodsCategoryVO> shopGoodsCategoryList;

    }

    @Data
    @ApiModel("ShopVO.SelfShopDetailVO")
    public static class SelfShopDetailVO implements Serializable{

        @ApiModelProperty(value = "店铺ID",position = 1)
        private String id;

        @ApiModelProperty(value = "店铺名称",position = 2)
        private String shopName;

        @ApiModelProperty(value = "店铺描述",position = 3)
        private String shopDesc;

        @ApiModelProperty(value = "店铺Logo",position = 4)
        private String shopLogo;

        @ApiModelProperty(value = "店主姓名",position = 5)
        private String personName;

        @ApiModelProperty(value = "店主身份证号",position = 6)
        private String shopManIdcardNo;

        @ApiModelProperty(value = "店主身份证正面",position = 7)
        private String shopManIdcardFront;

        @ApiModelProperty(value = "店主身份证反面",position = 8)
        private String shopManIdcardBack;

        @ApiModelProperty(value = "省ID",position = 7)
        private String shopProvince;

        @ApiModelProperty(value = "省名称",position = 7)
        private String shopProvinceText;

        @ApiModelProperty(value = "市ID",position = 8)
        private String shopCity;

        @ApiModelProperty(value = "市名称",position = 8)
        private String shopCityText;

        @ApiModelProperty(value = "县区ID",position = 9)
        private String shopCounty;

        @ApiModelProperty(value = "县区名称",position = 9)
        private String shopCountyText;

        @ApiModelProperty(value = "详细地址",position = 13)
        private String shopAddress;

        @ApiModelProperty(value = "手机号码",position = 14)
        private String shopManPhone;

        @ApiModelProperty("店主名字")
        private String shopManName;

        @ApiModelProperty(value = "邮箱",position = 15)
        private String shopManEmail;

        @ApiModelProperty(value = "经度",position = 12)
        private BigDecimal shopLongitude;

        @ApiModelProperty(value = "纬度",position = 12)
        private BigDecimal shopLatitude;


    }


    @Data
    @ApiModel("ShopVO.IdName")
    public static class IdNameVO implements Serializable{

        @ApiModelProperty("店铺ID")
        private String id;

        @ApiModelProperty("店铺名称")
        private String shopName;
    }

    @Data
    @ApiModel("ShopVO.ShopGoodsCategoryVO")
    public static class ShopGoodsCategoryVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("商品类目ID")
        private String categoryId;

        @ApiModelProperty("类目费用")
        private BigDecimal sharePrice;

        @ApiModelProperty("排序")
        private Integer idx;

    }


    @Data
    @ApiModel("ShopVO.InnerSimpleVO")
    public static class InnerSimpleVO implements Serializable{

        @ApiModelProperty("店铺ID")
        private String id;

        @ApiModelProperty("店铺名称")
        private String shopName;
    }
}
