package com.gs.lshly.common.struct.common;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
* @author xxfc
* @since 2020-11-02
*/
public abstract class CommonStockVO implements Serializable {


    @Data
    @ApiModel("CommonStockVO.InnerCheckStockVO")
    public static class InnerCheckStockVO implements Serializable {

        @ApiModelProperty(value = "店铺ID")
        private String shopId;

        @ApiModelProperty(value = "店铺名称")
        private String shopName;

        @ApiModelProperty(value = "检查结果总状态")
        private Integer checkState;

        @ApiModelProperty(value = "sku-id")
        private String skuId;

        @ApiModelProperty(value = "库存数量")
        private Integer  quantity;

    }
    @Data
    @ApiModel("CommonStockVO.InnerListCheckStockVO")
    public static class InnerListCheckStockVO implements Serializable {

        @ApiModelProperty(value = "店铺ID")
        private String shopId;

        @ApiModelProperty(value = "店铺名称")
        private String shopName;

        @ApiModelProperty(value = "检查结果总状态")
        private Integer checkState;

        @ApiModelProperty(value = "检查项结果数组")
        private List<InnerCheckItem> checkItemList;

    }

    @Data
    @ApiModel("CommonStockVO.InnerCheckItem")
    public static class InnerCheckItem implements  Serializable {


        @ApiModelProperty(value = "sku-id")
        private String skuId;

        @ApiModelProperty(value = "库存数量")
        private Integer  quantity;

        @ApiModelProperty(value = "查数结果状态")
        private Integer checkState;

    }

    @Data
    @ApiModel("CommonStockVO.InnerCountSalesVO")
    public static class InnerCountSalesVO implements Serializable {

        @ApiModelProperty(value = "店铺ID")
        private String shopId;

        @ApiModelProperty(value = "店铺名称")
        private String shopName;

        @ApiModelProperty(value = "商品ID")
        private String goodsId;

        @ApiModelProperty(value = "销售")
        private Integer salesVolume;
    }


    @Data
    @ApiModel("CommonStockVO.InnerComplexCountSalesVO")
    public static class InnerComplexCountSalesVO implements Serializable {

        @ApiModelProperty(value = "店铺ID")
        private String shopId;

        @ApiModelProperty(value = "店铺名称")
        private List<InnerSalesGoodsInfoItem>  innerSalesGoodsInfoItemList;
    }

    @Data
    @ApiModel("CommonStockDTO.InnerSalesGoodsInfoItem")
    public static class InnerSalesGoodsInfoItem implements Serializable {

        @ApiModelProperty(value = "商品ID")
        private String goodsId;

        @ApiModelProperty(value = "商品SKU-ID")
        private String skuId;

        @ApiModelProperty(value = "当前库存数量")
        private Integer quantity;

    }

    @Data
    @ApiModel("CommonStockVO.InnerChangeStockVO")
    public static class InnerChangeStockVO implements Serializable  {

        @ApiModelProperty(value = "店铺ID")
        private String shopId;

        @ApiModelProperty(value = "店铺名称")
        private String shopName;

        @ApiModelProperty(value = "商品当前库存信息数组")
        private List<InnerGoodsInfoItem> goodsInfoItemList;

    }

    @Data
    @ApiModel("CommonStockDTO.InnerGoodsInfoItem")
    public static class InnerGoodsInfoItem implements Serializable {

        @ApiModelProperty(value = "商品ID")
        private String goodsId;

        @ApiModelProperty(value = "商品SKU-ID")
        private String skuId;

        @ApiModelProperty(value = "当前库存数量")
        private Integer quantity;

    }


    @Data
    @ApiModel("CommonStockDTO.InnerStockVO")
    public static class InnerStockVO implements Serializable {

        @ApiModelProperty(value = "商品ID")
        private String goodsId;

        @ApiModelProperty(value = "商品SKU-ID")
        private String skuId;

        @ApiModelProperty(value = "当前库存数量")
        private Integer quantity;

    }

}
