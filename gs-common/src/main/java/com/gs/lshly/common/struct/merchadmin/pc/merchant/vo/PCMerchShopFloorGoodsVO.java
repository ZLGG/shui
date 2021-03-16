package com.gs.lshly.common.struct.merchadmin.pc.merchant.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
/**
* @author Starry
* @since 2020-11-04
*/
public abstract class PCMerchShopFloorGoodsVO implements Serializable {

    @Data
    @ApiModel("PCMerchShopFloorGoodsVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("商品ID")
        private String goodsId;


        @ApiModelProperty("楼层ID")
        private String shopFloorId;


        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("商家ID")
        private String merchantId;


        @ApiModelProperty("排序")
        private Integer idx;




    }

    @Data
    @ApiModel("PCMerchShopFloorGoodsVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
