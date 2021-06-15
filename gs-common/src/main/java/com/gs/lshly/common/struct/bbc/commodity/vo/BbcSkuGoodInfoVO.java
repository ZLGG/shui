package com.gs.lshly.common.struct.bbc.commodity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* @author Starry
* @since 2020-10-08
*/
public abstract class BbcSkuGoodInfoVO implements Serializable {

    @Data
    @ApiModel("BbcSkuGoodInfoVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

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
    @ApiModel("PCMerchSkuGoodInfoVO.DetailVO")
    public static class DetailVO extends ListVO {
        @ApiModelProperty(value = "sku库存数量")
        private Integer stockNum;
    }

    //--------------内部服务------------------

    @Data
    @ApiModel("BbcGoodsInfoVO.SkuVO")
    public static  class  SkuVO implements  Serializable{
        @ApiModelProperty(value = "skuId")
        private String skuId;

        @ApiModelProperty(value = "规格值")
        private String specValue;

        @ApiModelProperty("sku商品货号")
        private String skuGoodsNo;

        @ApiModelProperty("sku商品图片")
        private String image;

        @ApiModelProperty("sku商品售价")
        private BigDecimal skuSalePrice;

        @ApiModelProperty("sku库存")
        private Integer skuStock;

        @ApiModelProperty("办理备注")
        private String remarks;

        @ApiModelProperty("是否是积分商品")
        private Boolean isPointGood;

        @ApiModelProperty("是否是in会员礼品")
        private Boolean isInMemberGift;

        @ApiModelProperty("出售类型（0普通，1活动）")
        private Integer saleType;

        @ApiModelProperty("信天游产品号")
        private Integer thirdProductId;
        
        @ApiModelProperty("商品是否可购买 1:是  0：否")
        private Integer isBuy = 1;
        
        @ApiModelProperty("不能够买原因")
        private String buyRemark;
        
        @ApiModelProperty("积分价格")
        private Double pointPrice;
        
        @ApiModelProperty("in会员积分价格")
        private Double inMemberPointPrice;
    }
    
    @Data
    @ApiModel("BbcGoodsInfoVO.SkuVO")
    public static  class  SkuByGoodsVO implements  Serializable{
        
    	@ApiModelProperty(value = "skuId")
        private String skuId;

        @ApiModelProperty(value = "规格值")
        private String specValue;

        @ApiModelProperty("sku商品货号")
        private String skuGoodsNo;

        @ApiModelProperty("sku商品图片")
        private String image;

        @ApiModelProperty("sku商品售价")
        private BigDecimal skuSalePrice;

        @ApiModelProperty("sku库存")
        private Integer skuStock;

        @ApiModelProperty("积分价格")
        private Double pointPrice;

        @ApiModelProperty("办理备注")
        private String remarks;

        @ApiModelProperty("是否是积分商品")
        private Boolean isPointGood;

        @ApiModelProperty("是否是in会员礼品")
        private Boolean isInMemberGift;

        @ApiModelProperty("in会员积分价格")
        private Double inMemberPointPrice;

        @ApiModelProperty("出售类型（0普通，1活动）")
        private Integer saleType;

        @ApiModelProperty("信天游产品号")
        private Integer thirdProductId;
    }
}
