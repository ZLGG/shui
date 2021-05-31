package com.gs.lshly.common.struct.bbc.user.vo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
* @author xxfc
* @since 2020-10-28
*/
@SuppressWarnings("serial")
public abstract class BbcUserShoppingCarVO implements Serializable {
	
	@Data
    @ApiModel("BbcUserShoppingCarVO.HomeVO")
    @Accessors(chain = true)
    public static class HomeVO implements Serializable{

        @ApiModelProperty("购物车列表")
        private List<ListVO> carList;

        @ApiModelProperty("失效购物车列表")
        private List<ShoppingCarItemVO> loseList = new ArrayList<>();

    }
    @Data
    @ApiModel("BbcUserShoppingCarVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("商铺ID")
        private String shopId;

        @ApiModelProperty("商铺名称")
        private String shopName;

        @ApiModelProperty("购物车数组")
        private List<ShoppingCarItemVO> goodsList = new ArrayList<>();

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

        @ApiModelProperty("是否是积分商品")
        private Boolean isPointGood;

        @ApiModelProperty("商品价格")
        private BigDecimal goodsPrice;

        @ApiModelProperty("积分价格")
        private BigDecimal goodsPointPrice;

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

        @ApiModelProperty("店铺ID")
        @JsonIgnore
        private String shopId;

        @ApiModelProperty("兑换类型（虚拟，实物）")
        private Integer exchangeType;

        @ApiModelProperty("是否是in会员礼品")
        private Boolean isInMemberGift;

        @ApiModelProperty("in会员积分价格")
        private BigDecimal inMemberPointPrice;
        
        /**
         * 单品或者多规格商品（10 = 单品，20=多规格）
         */
    	@ApiModelProperty(value = "单品或者多规格商品（10 = 单品，20=多规格）")
        private Integer isSingle;
    }

    @Data
    @ApiModel("BbcUserVO.CountVO")
    public static class CountVO implements Serializable {

        @ApiModelProperty("角标数字")
        private Integer count;
    }

    /**
     * 购物车总计
     *
     * 
     * @author yingjun
     * @date 2021年5月17日 下午12:47:25
     */
	@Data
    @ApiModel("BbcUserShoppingCarVO.SummationVO")
    public static class SummationVO implements Serializable{
    	
    	@ApiModelProperty("现金价格")
        private BigDecimal price;

        @ApiModelProperty("积分价格")
        private BigDecimal pointPrice;
        
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

        @ApiModelProperty("是否是积分商品")
        private Boolean isPointGood;

        @ApiModelProperty("销售价")
        private BigDecimal salePrice;

        @ApiModelProperty("积分价格")
        private BigDecimal pointPrice;
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

        /**
         * 是否是积分商品
         */
        private Boolean isPointGood;

        /**
         * 积分价格
         */
        private BigDecimal pointPrice;

        /**
         * 兑换类型（虚拟，实物）
         */
        private Integer exchangeType;

        /**
         * 是否是in会员礼品
         */
        private Boolean isInMemberGift;

        /**
         * in会员积分价格
         */
        private BigDecimal inMemberPointPrice;

    }
    
	@Data
    @ApiModel("BbcUserShoppingCarVO.ShopSkuVO")
    public static class ShopSkuVO implements Serializable{
    	
    	@ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("SkuId")
        private List<SkuQuantityVO> skuQuantity;
        
    }
	
	@Data
    @ApiModel("BbcUserShoppingCarVO.SkuQuantityVO")
    public static class SkuQuantityVO implements Serializable{
    	
    	@ApiModelProperty("SKUID")
        private String skuId;
    	
        @ApiModelProperty("数量")
        private Integer quantity;
        
        @ApiModelProperty("CarId")
        private String carId;
        
        @ApiModelProperty("商品销售价格")
        private BigDecimal goodsPrice;
        
        @ApiModelProperty("积分价格")
        private BigDecimal goodsPointPrice;
        
        @ApiModelProperty("积分价格")
        private BigDecimal inMemberPointPrice;
        
        @ApiModelProperty("是否为积分商品")
        private Boolean isPointGood;
        
        @ApiModelProperty("是否为IN会员商品")
        private Boolean isInMemberGift;
        
        
    }
}
