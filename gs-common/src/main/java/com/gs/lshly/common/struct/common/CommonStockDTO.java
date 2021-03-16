package com.gs.lshly.common.struct.common;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* @author xxfc
* @since 2020-11-02
*/
public abstract class CommonStockDTO implements Serializable {


    @Data
    @ApiModel("BbcStockDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "ID")
        private String id;
    }


    @Data
    @ApiModel("BbcStockDTO.InnerCheckStockDTO")
    public static class InnerCheckStockDTO extends BaseDTO {

        @ApiModelProperty(value = "店铺ID")
        private String shopId;

        @ApiModelProperty(value = "商品信息数组")
        private List<InnerSkuGoodsInfoItem> goodsItemList = new ArrayList<>();

    }

    @Data
    @ApiModel("BbcStockDTO.InnerSkuGoodsInfoItem")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InnerSkuGoodsInfoItem implements Serializable {

        @ApiModelProperty(value = "商品ID")
        private String goodsId;

        @ApiModelProperty(value = "SKU-ID")
        private String skuId;

        @ApiModelProperty(value = "数量")
        private Integer quantity;

    }

    @Data
    @ApiModel("BbcStockDTO.InnerCountSalesDTO")
    @AllArgsConstructor
    public static class InnerCountSalesDTO extends BaseDTO {

        @ApiModelProperty(value = "时间段起")
        private LocalDateTime startTime;

        @ApiModelProperty(value = "时间段尾")
        private LocalDateTime endTime;

        @ApiModelProperty(value = "是否总销量[0=否 1=是]")
        private Integer isAll;

        @ApiModelProperty(value = "排序方式[10=升序 20=降序]")
        private Integer sortStyle;

        @ApiModelProperty(value = "商品ID数组")
        private List<String> goodsId;

    }

    @Data
    @ApiModel("BbcStockDTO.InnerChangeStockDTO")
    public static class InnerChangeStockDTO extends BaseDTO {

        @ApiModelProperty(value = "店铺ID")
        private String shopId;

        @ApiModelProperty(value = "商家id")
        private String merchantId;

        @ApiModelProperty(value = "仓库变动方向[10=增加 20=减少 30=初始化]")
        private Integer location;

        @ApiModelProperty(value = "数据来源[10=POS 20=商家运维 30=销售订单 40=采购单]")
        private Integer dataFromType;

        @ApiModelProperty(value = "商品信息数组")
        private List<InnerChangeStockItem> goodsItemList;

    }

    @Data
    @ApiModel("BbcStockDTO.InnerChangeStockItem")
    public static class InnerChangeStockItem implements Serializable {

        @ApiModelProperty(value = "商品ID")
        private String goodsId;

        @ApiModelProperty(value = "SKU-ID")
        private String skuId;

        @ApiModelProperty(value = "数量")
        private Integer quantity;

    }


}
