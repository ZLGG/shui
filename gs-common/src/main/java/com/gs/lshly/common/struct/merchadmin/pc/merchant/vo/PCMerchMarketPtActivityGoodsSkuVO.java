package com.gs.lshly.common.struct.merchadmin.pc.merchant.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author zdf
* @since 2020-12-02
*/
public abstract class PCMerchMarketPtActivityGoodsSkuVO implements Serializable {

    @Data
    @ApiModel("PCMerchMarketPtActivityGoodsSkuVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("活动ID")
        private String activityId;


        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("商家ID")
        private String merchantId;


        @ApiModelProperty("商家报名商品SPU项ID")
        private String goodsSpuItemId;


        @ApiModelProperty("商品ID")
        private String goodsId;

        @ApiModelProperty("skuID")
        private String skuId;

        @ApiModelProperty("sku活动价")
        private Float activitySaleSkuPrice;




    }

    @Data
    @ApiModel("PCMerchMarketPtActivityGoodsSkuVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
