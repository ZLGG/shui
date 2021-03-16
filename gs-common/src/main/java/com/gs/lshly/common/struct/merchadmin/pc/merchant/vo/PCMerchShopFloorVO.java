package com.gs.lshly.common.struct.merchadmin.pc.merchant.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
* @author Starry
* @since 2020-11-04
*/
public abstract class PCMerchShopFloorVO implements Serializable {

    @Data
    @ApiModel("PCMerchShopFloorVO.H5ListVO")
    @Accessors(chain = true)
    public static class H5ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("楼层名")
        private String name;

        @ApiModelProperty("商品最多显示数量")
        private Integer showNum;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("商品列表")
        private List<GoodsItem> goodsItemList;

    }

    @Data
    @ApiModel("PCMerchShopFloorVO.GoodsItem")
    @Accessors(chain = true)
    public static class GoodsItem implements Serializable {

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("商品名称")
        private String goodsName;
    }


    @Data
    @ApiModel("PCMerchShopFloorVO.PCListVO")
    @Accessors(chain = true)
    public static class PCListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("楼层名")
        private String name;

        @ApiModelProperty("商品最多显示数量")
        private Integer showNum;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("商品列表")
        private List<PCGoodsItem> goodsItemList;

    }

    @Data
    @ApiModel("PCMerchShopFloorVO.PCGoodsItem")
    @Accessors(chain = true)
    public static class PCGoodsItem implements Serializable {

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("商品名称")
        private String goodsName;
    }

}
