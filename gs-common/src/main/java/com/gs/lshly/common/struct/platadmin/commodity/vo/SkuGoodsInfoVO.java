package com.gs.lshly.common.struct.platadmin.commodity.vo;

import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchSkuGoodInfoVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author Starry
 * @Date 17:41 2020/10/14
 */
public abstract class SkuGoodsInfoVO implements Serializable{

    @Data
    @ApiModel("SkuGoodInfoVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable {

        @ApiModelProperty("sku商品id")
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


        @ApiModelProperty("操作人")
        private String operator;


    }

    @Data
    @ApiModel("SkuGoodInfoVO.DetailVO")
    public static class DetailVO extends PCMerchSkuGoodInfoVO.ListVO {
        @ApiModelProperty(value = "sku库存数量")
        private Integer stockNum;
    }
}
