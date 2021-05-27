package com.gs.lshly.common.struct.bbc.user.dto;
import java.io.Serializable;
import java.util.List;

import com.gs.lshly.common.struct.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* @author xxfc
* @since 2020-10-28
*/
@SuppressWarnings("serial")
public abstract class BbcUserShoppingCarDTO implements Serializable {
	@EqualsAndHashCode(callSuper=false)
	@Data
    @ApiModel("BbcUserShoppingCarDTO.ETO")
    @Accessors(chain = true)
    public static class ModifySkuDTO extends BaseDTO {

		@ApiModelProperty("id")
        private String id;

        @ApiModelProperty("SKU-ID")
        private String skuId;

        @ApiModelProperty("数量")
        private Integer quantity;
    }
	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("BbcUserShoppingCarDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

//        @ApiModelProperty("商品ID")
//        private String goodsId;
//
//        @ApiModelProperty("商品名称")
//        private String goodsName;
//
//        @ApiModelProperty("商品副标题")
//        private String goodsTitle;
//
//        @ApiModelProperty("商品价格")
//        private BigDecimal goodsPrice;

        @ApiModelProperty("SKU-ID")
        private String skuId;

//        @ApiModelProperty("SKU-图片")
//        private String skuImage;
//
//        @ApiModelProperty("规格值")
//        private String specValue;
//
//        @ApiModelProperty("店铺ID")
//        private String shopId;
//
//        @ApiModelProperty("店铺名称")
//        private String shopName;

        @ApiModelProperty("数量")
        private Integer quantity;
    }
	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("BbcUserShoppingCarDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }
	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("BbcUserShoppingCarDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty(value = "购物车ID数组")
        private List<String> idList;
    }
	@EqualsAndHashCode(callSuper=false)
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
	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("BbcUserShoppingCarDTO.SelectDTO")
    @AllArgsConstructor
    public static class SelectDTO extends BaseDTO {

        @ApiModelProperty(value = "购物车ID",hidden = true)
        private String id;

    }
	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("BbcUserShoppingCarDTO.SelectAllDTO")
    @AllArgsConstructor
    public static class SelectAllDTO extends BaseDTO {

        @ApiModelProperty(value = "购物车ID数组")
        private List<String> idList;

        @ApiModelProperty(value = "选中状态[0=否 1=是]")
        private Integer isSelect;
    }
	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("BbcUserShoppingCarDTO.InnerIdListDTO")
    public static class InnerIdListDTO extends BaseDTO {

        @ApiModelProperty(value = "购物车ID数组")
        private List<String> idList;

    }
}
