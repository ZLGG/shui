package com.gs.lshly.common.struct.bbb.h5.user.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-28
*/
public abstract class BbbH5UserShoppingCarDTO implements Serializable {

    @Data
    @ApiModel("BbcUserShoppingCarDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

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

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("数量")
        private Integer quantity;
    }

    @Data
    @ApiModel("BbcUserShoppingCarDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("BbcUserShoppingCarDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty(value = "购物车ID数组")
        private List<String> idList;
    }

    @Data
    @ApiModel("BbcUserShoppingCarDTO.QuantityDTO")
    @AllArgsConstructor
    public static class QuantityDTO extends BaseDTO {

        @ApiModelProperty(value = "购物车ID",hidden = true)
        private String id;

        @ApiModelProperty(value = "数量")
        private Integer quantity;

        @ApiModelProperty(value = "变动方向[10=增加 20=减少]")
        private Integer location;

    }

    @Data
    @ApiModel("BbcUserShoppingCarDTO.SelectDTO")
    @AllArgsConstructor
    public static class SelectDTO extends BaseDTO {

        @ApiModelProperty(value = "购物车ID",hidden = true)
        private String id;

    }

    @Data
    @ApiModel("BbcUserShoppingCarDTO.SelectAllDTO")
    @AllArgsConstructor
    public static class SelectAllDTO extends BaseDTO {

        @ApiModelProperty(value = "购物车ID数组")
        private List<String> idList;

        @ApiModelProperty(value = "选中状态[0=否 1=是]")
        private Integer isSelect;
    }

    @Data
    @ApiModel("BbcUserShoppingCarDTO.InnerIdListDTO")
    public static class InnerIdListDTO extends BaseDTO {

        @ApiModelProperty(value = "购物车ID数组")
        private List<String> idList;

    }

}
