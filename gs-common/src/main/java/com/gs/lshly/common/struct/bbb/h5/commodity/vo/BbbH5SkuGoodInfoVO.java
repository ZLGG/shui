package com.gs.lshly.common.struct.bbb.h5.commodity.vo;

import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsInfoVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
* @author Starry
* @since 2020-10-08
*/
public abstract class BbbH5SkuGoodInfoVO implements Serializable {

    @Data
    @ApiModel("BbbH5SkuGoodInfoVO.ListVO")
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
    @ApiModel("BbbH5SkuGoodInfoVO.DetailVO")
    public static class DetailVO extends ListVO {
        @ApiModelProperty(value = "sku库存数量")
        private Integer stockNum;
    }



    @Data
    @ApiModel("BbbH5SkuGoodInfoVO.SkuVO")
    public static  class  SkuVO implements  Serializable{
        @ApiModelProperty(value = "skuId")
        private String skuId;

        @ApiModelProperty("商品id")
        private String goodId;

        @ApiModelProperty("商家id")
        private String merchantId;

        @ApiModelProperty("店铺id")
        private String shopId;

        @ApiModelProperty("规格id组")
        private String specsKey;


        @ApiModelProperty("规格值组")
        private String specsValue;

        @ApiModelProperty("sku图片")
        private String skuImg;

        @ApiModelProperty("sku商品货号")
        private String skuGoodsNo;

        @ApiModelProperty("sku商品售价")
        private BigDecimal skuSalePrice;

        @ApiModelProperty("sku商品市场价")
        private BigDecimal skuOldPrice;

        @ApiModelProperty("商品阶梯价")
        private List<BbbH5GoodsInfoVO.GoodsStepPriceVO> skuStepPrice;

        @ApiModelProperty("商品批发价")
        private BigDecimal wholesalePrice;

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

    }
}
