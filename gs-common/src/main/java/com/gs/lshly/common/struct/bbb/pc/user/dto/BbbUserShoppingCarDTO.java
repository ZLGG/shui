package com.gs.lshly.common.struct.bbb.pc.user.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-28
*/
public abstract class BbbUserShoppingCarDTO implements Serializable {

    @Data
    @ApiModel("BbbUserShoppingCarDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty("店铺ID")
        @NotBlank
        private String shopId;

        @ApiModelProperty("店铺名称")
        @NotBlank
        private String shopName;

        @ApiModelProperty("购物车商品数据数组")
        private List<ShoppingCarItemETO> itemList;

    }
    @Data
    @ApiModel("BbbUserShoppingCarDTO.AddShopingETO")
    @Accessors(chain = true)
    public static class AddShopingETO extends BaseDTO {

        @ApiModelProperty("购物项数组")
        private List<AddItem> skuGoodsList = new ArrayList<>();
    }

    @Data
    @ApiModel("BbbUserShoppingCarDTO.AddItem")
    @Accessors(chain = true)
    public static class AddItem implements Serializable {

        @ApiModelProperty("SKU-ID")
        private String skuId;

        @ApiModelProperty("数量")
        private Integer quantity;
    }
    
    @Data
    @ApiModel("BbbUserShoppingCarDTO.ShoppingCarItemETO")
    @Accessors(chain = true)
    public static class ShoppingCarItemETO implements Serializable {

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
    @ApiModel("BbbUserShoppingCarDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("BbbUserShoppingCarDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty(value = "购物车ID数组")
        private List<String> idList;

    }

    @Data
    @ApiModel("BbbUserShoppingCarDTO.QuantityDTO")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QuantityDTO extends BaseDTO {

        @ApiModelProperty(value = "购物车ID",hidden = true)
        private String id;

        @ApiModelProperty(value = "数量")
        private Integer quantity;

    }

    @Data
    @ApiModel("BbbUserShoppingCarDTO.SelectDTO")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SelectDTO extends BaseDTO {

        @ApiModelProperty(value = "购物车ID",hidden = true)
        private String id;

    }

    @Data
    @ApiModel("BbbUserShoppingCarDTO.SelectAllDTO")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SelectAllDTO extends BaseDTO {

        @ApiModelProperty(value = "购物车ID数组")
        private List<String> idList;

        @ApiModelProperty(value = "选中状态[0=否 1=是]")
        private Integer isSelect;
    }

    @Data
    @ApiModel("BbbUserShoppingCarDTO.InnerIdListDTO")
    public static class InnerIdListDTO extends BaseDTO {

        @ApiModelProperty(value = "购物车ID数组")
        private List<String> idList;

    }

    @Data
    @ApiModel("BbbUserShoppingCarDTO.QueryGradePriceETO")
    @Accessors(chain = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QueryGradePriceDTO extends BaseDTO {

        @ApiModelProperty("商品SKU-ID")
        private String skuId;

    }

}
