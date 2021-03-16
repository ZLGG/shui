package com.gs.lshly.common.struct.bbb.pc.user.vo;

import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsInfoVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-28
*/
public abstract class BbbUserShoppingCarVO implements Serializable {



    @Data
    @ApiModel("BbbUserShoppingCarVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("购物车数组")
        private List<ShoppingCarItemVO> skuGoodsList = new ArrayList<>();

    }

    @Data
    @ApiModel("BbbUserShoppingCarVO.ShoppingCarItemVO")
    public static class ShoppingCarItemVO implements Serializable {

        @ApiModelProperty("购物车ID")
        private String id;

        @ApiModelProperty("商品id")
        private String goodsId;

        @ApiModelProperty("sku商品id")
        private String skuId;

        @ApiModelProperty("商品默认图片")
        private String goodsImage;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品标题")
        private String goodsTitle;

        @ApiModelProperty("商品货号")
        private String goodsNo;

        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;

        @ApiModelProperty("sku规格值")
        private String skuSpecValue;

        @ApiModelProperty("SKU商品货号")
        private String skuGoodsNo;

        @ApiModelProperty("商品状态")
        private Integer goodsState;

        @ApiModelProperty("数量")
        private Integer quantity;

        @ApiModelProperty("是否选中[0=否 1=是]")
        private Integer isSelect;

        @ApiModelProperty("库存数量")
        private Integer stockNum = 0 ;

        @ApiModelProperty("商品阶梯价")
        private List<PCBbbGoodsInfoVO.GoodsStepPriceVO> stepPrice;
    }

    @Data
    @ApiModel("BbbUserShoppingCarVO.CountVO")
    public static class CountVO implements Serializable {

        @ApiModelProperty("角标数字")
        private Integer count;
    }



    @Data
    @ApiModel("BbbUserShoppingCarVO.InnerSimpleVO")
    @Accessors(chain = true)
    public static class InnerSimpleVO implements Serializable{

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("购物车简单数组")
        private List<InnerSimpleItem> itemList;

    }

    @Data
    @ApiModel("BbbUserShoppingCarVO.InnerSimpleItem")
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
    @ApiModel("BbbUserShoppingCarVO.InnerListVO")
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
    @ApiModel("BbbUserShoppingCarVO.InnerCarItemVO")
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
    public static class ChangeQuantityVO implements Serializable {

        @ApiModelProperty("购物车ID")
        private String id;

        @ApiModelProperty("商品价格")
        private BigDecimal goodsPrice;

        @ApiModelProperty("总价（SKU当前价 * 数量）")
        private BigDecimal totalPrice;

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
