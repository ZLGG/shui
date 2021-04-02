package com.gs.lshly.common.struct.merchadmin.pc.commodity.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;

/**
* @author Starry
* @since 2020-10-08
*/
public abstract class PCMerchSkuGoodInfoDTO implements Serializable {

    @Getter
    @Setter
    @ApiModel("PCMerchSkuGoodInfoDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "sku商品id",hidden = true)
        private String id;

        @ApiModelProperty("商品id")
        private String goodId;

        @ApiModelProperty("商家id")
        private String merchantId;

        @ApiModelProperty("店铺id")
        private String shopId;

        @ApiModelProperty("类目id")
        private String categoryId;

        @ApiModelProperty("规格id组")
        private String specsKey;

        @ApiModelProperty("规格值组")
        private String specsValue;

        @ApiModelProperty("sku商品货号")
        private String skuGoodsNo;

        @ApiModelProperty("条形码")
        private String barcode;

        @ApiModelProperty("商品图片")
        private String image;

        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;

        @ApiModelProperty("商品原价")
        private BigDecimal oldPrice;


        @ApiModelProperty("商品成本价")
        private BigDecimal costPrice;


        @ApiModelProperty("商品阶梯价")
        private String stepPrice;

        @ApiModelProperty("商品状态")
        private Integer state;


    }

    @Data
    @ApiModel("PCMerchSkuGoodInfoDTO.AddETO")
    public static class AddETO extends ETO{
        @ApiModelProperty(value = "sku库存")
        private Integer skuStock;
    }

    @Data
    @ApiModel("PCMerchSkuGoodInfoDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "sku商品id")
        private String id;
    }

    @Data
    @ApiModel("PCMerchSkuGoodInfoDTO.StepPriceDTO")
    public static class StepPriceDTO implements Serializable{
        @ApiModelProperty(value = "区间")
        private String key;

        @ApiModelProperty(value = "区间对应的价格")
        private BigDecimal value;
    }

    @Data
    @ApiModel("PCMerchSkuGoodInfoDTO.GoodId")
    public static class GoodIdDTO extends BaseDTO {

        @ApiModelProperty(value = "商品id")
        private String goodId;
    }
}
