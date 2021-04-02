package com.gs.lshly.common.struct.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-06
*/
public abstract class CommonShopVO implements Serializable {



    @Data
    @ApiModel("CommonShopVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable {

        @ApiModelProperty(value = "店铺ID",hidden = true)
        private String id;

        @ApiModelProperty("店铺商家类型[10=2b 20=2c]")
        private Integer shopMerchantType;

        @ApiModelProperty("店铺类型[10=品牌旗舰店 20=品牌专卖店 30=类目专营店 40=运营商自营 50=多品类通用型]")
        private Integer shopType;

        @ApiModelProperty(value = "商品分类数组")
        private List<CategoryVO> categoryList;

        @ApiModelProperty("品牌信息")
        private BrandVO brand;

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

        @ApiModelProperty("店铺logo")
        private String shopLogo;

        @ApiModelProperty("店铺类型")
        private String shopTypeName;

        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime openTime;

        @ApiModelProperty("开通状态[10=营业 20=关闭]")
        private Integer shopState;

        @ApiModelProperty("经营品牌")
        private String brandName;

        @ApiModelProperty("商家来源[10=平台入驻 20=平台添加]")
        private String shopMerchantFrom;

    }

    @Data
    @ApiModel("CommonShopDTO.BrandVO")
    @Accessors(chain = true)
    public static class BrandVO implements Serializable {

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
    @ApiModel("CommonShopDTO.CategoryVO")
    @Accessors(chain = true)
    public static class CategoryVO implements Serializable {

        @ApiModelProperty(value = "商品分类ID")
        private String goodsCategoryId;

        @ApiModelProperty("商品分类名称")
        private String goodsCategoryName;

    }

    @Data
    @ApiModel("CommonShopVO.ShopIdNameVO")
    @Accessors(chain = true)
    public static class ShopIdNameVO implements Serializable {

        @ApiModelProperty(value = "店铺ID")
        private String id;

        @ApiModelProperty(value = "商家ID")
        private String merchantId;

        @ApiModelProperty(value = "店铺名称")
        private String shopName;
    }

    @Data
    @ApiModel("CommonShopVO.SimpleVO")
    @Accessors(chain = true)
    public static class SimpleVO implements Serializable {

        @ApiModelProperty("店铺ID")
        private String id;

        @ApiModelProperty("商家id")
        private String merchantId;

        @ApiModelProperty("pos店门店id")
        private String posShopId;

        @ApiModelProperty("店铺Logo")
        private String shopLogo;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺类型")
        private Integer shopType;

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
            return shopProvince + shopCity+ shopCounty + shopAddress;
        }
    }

    @Data
    @ApiModel("CommonShopVO.ShopGoodsCategoryVO")
    @Accessors(chain = true)
    public static class ShopGoodsCategoryVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("商品类目ID")
        private String categoryId;

        @ApiModelProperty("商品类目名称")
        private String categoryName;

        @ApiModelProperty("商品类目父ID")
        private String categoryPid;

        @ApiModelProperty("类目平台")
        private Integer useFiled;

    }

    @Data
    @ApiModel("CommonShopVO.ShopCategoryInfoVO")
    @Accessors(chain = true)
    public static class ShopCategoryInfoVO implements Serializable{

        @ApiModelProperty("商品类目名称")
        private String categoryName;

        @ApiModelProperty("平台使用费")
        private BigDecimal sharePrice;

    }


    @Data
    @ApiModel("CommonShopDTO.NavigationVO")
    @Accessors(chain = true)
    public static class NavigationVO implements Serializable {

        @ApiModelProperty(value = "店铺分类-级ID")
        private String navigationId;

        @ApiModelProperty(value = "店铺分类-级名称")
        private String navigationName;

        @ApiModelProperty(value = "店铺分类2级ID")
        private String navigationTowId;

        @ApiModelProperty(value = "店铺分类2级名称")
        private String navigationTowName;

    }


    @Data
    @ApiModel("CommonShopDTO.MerchantVO")
    @Accessors(chain = true)
    public static class MerchantVO implements Serializable {

        @ApiModelProperty(value = "店铺ID")
        private String shopId;

        @ApiModelProperty(value = "店铺名称")
        private String shopName;

        @ApiModelProperty(value = "商家Id")
        private String merchantId;

        @ApiModelProperty(value = "商家名称")
        private String merchantName;

        @ApiModelProperty(value = "是否是自营商家[0=否,1=是]")
        private Integer isPlatform;

        @ApiModelProperty(value = "店铺类型[10=品牌旗舰店 20=品牌专卖店 30=类目专营店 40=运营商自营 50=多品类通用型]")
        private Integer shopType;

        @ApiModelProperty(value = "非自营店铺,店铺申请的所有一级商品分类")
        private List<CategoryVO> categoryList  = new ArrayList<>();

    }

    @Data
    @ApiModel("CommonShopVO.SimpleDetailVO")
    public static class SimpleDetailVO implements Serializable{

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

    }

    @Data
    @ApiModel("CommonShopVO.MerchantListVO")
    public static class MerchantListVO implements Serializable{

        @ApiModelProperty("商家ID")
        private String id;

        @ApiModelProperty("商家名称")
        private String merchantName;

    }


    @Data
    @ApiModel("CommonShopVO.NavigationListVO")
    @Accessors(chain = true)
    public static class NavigationListVO implements Serializable{

        @ApiModelProperty(value = "id",position = 1)
        private String id;

        @ApiModelProperty(value = "分类名称",position = 2)
        private String navName;

        @ApiModelProperty(value = "排序",position = 2)
        private Integer idx;

        @ApiModelProperty(value = "子分类节点数组",position = 6)
        List<NavigationChildVO> childList = new ArrayList<>();
    }

    @Data
    @ApiModel("CommonShopVO.NavigationChildVO")
    @NoArgsConstructor
    public static class NavigationChildVO implements Serializable{

        @ApiModelProperty(value = "id",position = 1)
        private String id;

        @ApiModelProperty(value = "排序",position = 2)
        private Integer idx;

        @ApiModelProperty(value = "分类名称",position = 2)
        private String navName;

    }

    @Data
    @ApiModel("CommonShopVO.ShopServiceVO")
    public static class ShopServiceVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("账号")
        private String account;

        @ApiModelProperty("账号类型[10=qq 20=其它]")
        private String type;

        @ApiModelProperty("状态/是否启用[10=启用 20=禁用]")
        private Integer state;

        @ApiModelProperty("热线电话")
        private String phone;

        @ApiModelProperty("电话启用状态[10=启用 20=禁用]")
        private Integer phoneState;

        @ApiModelProperty("店铺ID")
        private String shopId;
    }

    @Data
    @ApiModel("CommonShopVO.ShopServiceOutVO")
    public static class ShopServiceOutVO implements Serializable{

        @ApiModelProperty(value = "QQ号")
        private String account;

        @ApiModelProperty(value = "热线电话")
        private String phone;
    }


}
