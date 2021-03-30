package com.gs.lshly.common.struct.merchadmin.pc.commodity.vo;
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
public abstract class PCMerchSkuGoodInfoVO implements Serializable {

    @Data
    @ApiModel("PCMerchSkuGoodInfoVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("sku商品id")
        private String id;


        @ApiModelProperty("pos店铺商品id")
        private String posSpuId;

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

        @ApiModelProperty("视频地址")
        private String videoUrl;

    }

    @Data
    @ApiModel("PCMerchSkuGoodInfoVO.DetailVO")
    public static class DetailVO extends ListVO {
        @ApiModelProperty(value = "sku库存数量")
        private Integer skuStock;
    }

}
