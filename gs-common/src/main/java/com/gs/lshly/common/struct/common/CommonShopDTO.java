package com.gs.lshly.common.struct.common;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-06
*/
public abstract class CommonShopDTO implements Serializable {



    @Data
    @ApiModel("CommonShopDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty("店铺商家类型[10=2b 20=2c]")
        private String shopMerchantType;

        @ApiModelProperty("店铺类型[10=品牌旗舰店 20=品牌专卖店 30=类目专营店 40=运营商自营 50=多品类通用型]")
        private Integer shopType;

        @ApiModelProperty("商品分类数组")
        private List<CategoryETO> categoryList;

        @ApiModelProperty("品牌信息")
        private BrandETO brand;

        @ApiModelProperty("店铺LOGO")
        private String shopLogo;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺描述")
        private String shopDesc;

        @ApiModelProperty("店主姓名")
        private String shopManName;

        @ApiModelProperty("店主身份证复印件(正)")
        private String shopManIdcardFront;

        @ApiModelProperty("店主身份证复印件(反)")
        private String shopManIdcardBack;

        @ApiModelProperty("店铺地址")
        private String shopAddress;

        @ApiModelProperty("店铺联系人手机号")
        private String shopManPhone;

        @ApiModelProperty("店铺联系人邮箱")
        private String shopManEmail;

        @ApiModelProperty("商家来源[10=平台入驻 20=平台添加]")
        private String shopMerchantFrom;

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

    }

    @Data
    @ApiModel("CommonShopDTO.ShopSimpleETO")
    @Accessors(chain = true)
    public static class ShopSimpleETO extends BaseDTO {

        @ApiModelProperty("店铺商家类型[10=2b 20=2c]")
        private String shopMerchantType;

        @ApiModelProperty("店铺类型[10=品牌旗舰店 20=品牌专卖店 30=类目专营店 40=运营商自营 50=多品类通用型]")
        private Integer shopType;

        @ApiModelProperty("店铺LOGO")
        private String shopLogo;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺描述")
        private String shopDesc;

        @ApiModelProperty("店主姓名")
        private String shopManName;

        @ApiModelProperty("店主身份证复印件(正)")
        private String shopManIdcardFront;

        @ApiModelProperty("店主身份证复印件(反)")
        private String shopManIdcardBack;

        @ApiModelProperty("店铺地址")
        private String shopAddress;

        @ApiModelProperty("店铺联系人手机号")
        private String shopManPhone;

        @ApiModelProperty("店铺联系人邮箱")
        private String shopManEmail;

        @ApiModelProperty("商家来源[10=平台入驻 20=平台添加]")
        private String shopMerchantFrom;

    }

    @Data
    @ApiModel("CommonShopDTO.BrandETO")
    @Accessors(chain = true)
    public static class BrandETO extends BaseDTO {

        @ApiModelProperty(value = "品牌ID")
        private String brandId;

        @ApiModelProperty(value = "品牌名称")
        private String brandName;

        @ApiModelProperty(value = "是否新增")
        private Integer brandIsNew;

        @ApiModelProperty("品牌经营授权复印件")
        private String brandCert;
    }

    @Data
    @ApiModel("CommonShopDTO.CategoryETO")
    @Accessors(chain = true)
    public static class CategoryETO extends BaseDTO {

        @ApiModelProperty(value = "商品分类ID")
        private String goodsCategoryId;

        @ApiModelProperty("商品分类名称")
        private String goodsCategoryName;

    }

    @Data
    @ApiModel("CommonShopDTO.IdDTO")
    @Accessors(chain = true)
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "店铺ID")
        private String id;

    }

    @Data
    @ApiModel("CommonShopDTO.NavigationDTO")
    @Accessors(chain = true)
    public static class NavigationDTO extends BaseDTO {

        @ApiModelProperty(value = "终端[10=2b 20=2c]")
        @NotNull
        private Integer terminal;

    }

    @Data
    @ApiModel("CommonShopDTO.NavigationByDTO")
    @Accessors(chain = true)
    public static class NavigationByDTO extends BaseDTO {

        @ApiModelProperty(value = "终端[10=2b 20=2c]")
        private Integer terminal;

        private String navName;

        private Integer level;

        private String shopId;

    }
    @Data
    @ApiModel("CommonShopDTO.VisitsDTO")
    @Accessors(chain = true)
    public static class VisitsDTO extends BaseDTO {

        @ApiModelProperty("店铺ID")
        private String shopId;

    }
}
