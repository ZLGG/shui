package com.gs.lshly.common.struct.platadmin.merchant.dto;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.LegalDictDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;


/**
* @author xxfc
* @since 2020-10-13
*/
public abstract class ShopDTO implements Serializable {




    @Data
    @ApiModel("CommonShopDTO.ComplexETO")
    @Accessors(chain = true)
    public static class ComplexETO extends BaseDTO {

        LegalDictDTO.ETO LegalDTO;

        ShopDTO.ETO shopETO;

    }


    @Data
    @ApiModel("CommonShopDTO.ETO")
    @NoArgsConstructor
    public static class ETO extends BaseDTO{

        @ApiModelProperty(value = "店铺商家类型[10=2b 20=2c]",hidden = true,position = 28)
        private Integer terminal;

        @ApiModelProperty(value = "店铺类型[10=品牌旗舰店 20=品牌专卖店 30=类目专营店 40=运营商自营 50=多品类通用型]",position = 29)
        private Integer shopType;

        @ApiModelProperty(value = "店铺ID",position = 1,hidden = true)
        private String id;

        @ApiModelProperty(value = "店铺名称",position = 1)
        private String shopName;

        @ApiModelProperty(value = "店铺描述",position = 2)
        private String shopDesc;

        @ApiModelProperty(value = "店铺Logo",position = 3)
        private String shopLogo;

        @ApiModelProperty(value = "品牌ID",position = 31)
        private String brandId;

        @ApiModelProperty(value = "品牌名称",position = 32)
        private String brandName;

        @ApiModelProperty(value = "品牌经营授权复印件",position = 33)
        private String brandCert;

        @ApiModelProperty(value = "店主姓名",position = 4)
        private String shopManName;

        @ApiModelProperty(value = "店主身份证号",position = 5)
        private String shopManIdcardNo;

        @ApiModelProperty(value = "店主身份证正面",position = 6)
        private String shopManIdcardFront;

        @ApiModelProperty(value = "店主身份证反面",position = 7)
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

        @ApiModelProperty(value = "经度",position = 12)
        private BigDecimal shopLongitude;

        @ApiModelProperty(value = "纬度",position = 12)
        private BigDecimal shopLatitude;

        @ApiModelProperty(value = "详细地址",position = 12)
        private String shopAddress;

        @ApiModelProperty(value = "手机号码",position = 13)
        private String shopManPhone;

        @ApiModelProperty(value = "邮箱",position = 14)
        private String shopManEmail;

        public void validLongitudeLatitude(){
            if (shopLongitude != null || shopLatitude != null) {
                if (shopLongitude.intValue() > 180) {
                    throw new BusinessException("经度不能大于180");
                }
                if (shopLatitude.intValue() > 90) {
                    throw new BusinessException("纬度不能大于90");
                }
            }
        }

    }

    @Data
    @ApiModel("CommonShopDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        @ApiModelProperty(value = "店铺ID")
        private String id;
    }


    @Data
    @ApiModel("CommonShopDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {
        @ApiModelProperty(value = "店铺ID列表")
        private String id;
    }


    @Data
    @ApiModel("CommonShopDTO.SetShopGoodsCategoryPiceDTO")
    @AllArgsConstructor
    public static class SetShopGoodsCategoryPiceDTO extends BaseDTO {

        @ApiModelProperty(value = "商品类目ID")
        private String id;

        @ApiModelProperty("类目费用")
        private BigDecimal sharePrice;

    }

    @Data
    @ApiModel("CommonShopDTO.ShopGoodsCategoryIdDTO")
    @AllArgsConstructor
    public static class ShopGoodsCategoryIdDTO extends BaseDTO {

        @ApiModelProperty(value = "商品类目费用ID")
        private String id;

    }


    @Data
    @ApiModel("CommonShopDTO.innerIdListDTO")
    public static class innerIdListDTO extends BaseDTO {

        @ApiModelProperty(value = "店铺ID列表")
        private String id;

    }


}
