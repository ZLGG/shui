package com.gs.lshly.common.struct.bbb.h5.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-28
*/
public abstract class BbbH5UserShoppingCarVO implements Serializable {

    @Data
    @ApiModel("BbcUserShoppingCarVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("商铺ID")
        private String shopId;

        @ApiModelProperty("商铺名称")
        private String shopName;

        @ApiModelProperty("购物车数组")
        private List<ShoppingCarItemVO> goodsList;

    }

    @Data
    @ApiModel("BbcUserVO.ShoppingCarItemVO")
    public static class ShoppingCarItemVO implements Serializable {

        @ApiModelProperty("购物车ID")
        private String id;

        @ApiModelProperty("商品ID")
        private String goodsId;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品副标题")
        private String goodsTitle;

        @ApiModelProperty("商品价格")
        private BigDecimal goodsPrice;

        @ApiModelProperty("SKU-ID")
        private String skuId;

        @ApiModelProperty("SKU-图片")
        private String skuImage;

        @ApiModelProperty("规格值")
        private String specValue;

        @ApiModelProperty("数量")
        private Integer quantity;

        @ApiModelProperty("是否选中[0=否 1=是]")
        private Integer isSelect;
    }

    @Data
    @ApiModel("BbcUserVO.CountVO")
    public static class CountVO implements Serializable {

        @ApiModelProperty("角标数字")
        private Integer count;
    }



    @Data
    @ApiModel("BbcUserShoppingCarVO.InnerSimpleVO")
    @Accessors(chain = true)
    public static class InnerSimpleVO implements Serializable{

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("购物车简单数组")
        private List<InnerSimpleItem> itemList;

    }

    @Data
    @ApiModel("BbcUserShoppingCarVO.InnerSimpleItem")
    @Accessors(chain = true)
    public static class InnerSimpleItem implements Serializable{

        @ApiModelProperty("购物车ID")
        private String id;

        @ApiModelProperty("skuId")
        private String skuId;

        @ApiModelProperty("数量")
        private Integer quantity;

        @ApiModelProperty("销售价")
        private BigDecimal salePrice;

    }

    @Data
    @ApiModel("BbcUserShoppingCarVO.InnerListVO")
    @Accessors(chain = true)
    public static class InnerListVO implements Serializable{

        @ApiModelProperty("商铺ID")
        private String shopId;

        @ApiModelProperty("商铺名称")
        private String shopName;

        @ApiModelProperty("购物车数组")
        private List<InnerCarItemVO> goodsList;

        @ApiModelProperty("SKU-Id数组")
        private List<String> skuIdList;

    }

    @Data
    @ApiModel("BbcUserVO.InnerCarItemVO")
    public static class InnerCarItemVO implements Serializable {

        @ApiModelProperty("购物车ID")
        private String id;

        @ApiModelProperty("商品ID")
        private String goodsId;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品副标题")
        private String goodsTitle;

        @ApiModelProperty("商品价格")
        private BigDecimal goodsPrice;

        @ApiModelProperty("SKU-ID")
        private String skuId;

        @ApiModelProperty("SKU-图片")
        private String skuImage;

        @ApiModelProperty("规格值")
        private String specValue;

        @ApiModelProperty("数量")
        private Integer quantity;
    }

    @Data
    @Accessors(chain = true)
    public static class InnerSkuInfoVO implements Serializable {

        String specValue;

        BigDecimal skuPrice;

        String skuImage;

        String shopId;

        String shopName;

        String goodsId;

        String goodsName;

        String goodsTitle;

    }
}
