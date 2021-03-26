package com.gs.lshly.common.struct.bbc.user.vo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-28
*/
public abstract class BbcUserShoppingCarVO implements Serializable {

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

        @ApiModelProperty("店铺ID")
        @JsonIgnore
        private String shopId;
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
        private Double inMemberPointPrice;

    }
}
