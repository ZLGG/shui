package com.gs.lshly.common.struct.merchadmin.pc.merchant.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


/**
* @author zdf
* @since 2020-12-01
*/
public abstract class PCMerchMarketPtActivityGoodsSpuDTO implements Serializable {

    @Data
    @ApiModel("PCMerchMarketPtActivityGoodsSpuDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("活动ID")
        private String activityId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String merchantId;

        @ApiModelProperty("活动名称")
        private String name;
        /**
         * sku活动价
         */
        private BigDecimal activitySalePrice;
        @ApiModelProperty("标签")
        private String label;

        @ApiModelProperty("描述")
        private String activityDescribe;

        @ApiModelProperty("商品ID")
        private String goodsId;
    }

    @Data
    @ApiModel("PCMerchMarketPtActivityGoodsSpuDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    //用于报名
    @Data
    @ApiModel("PCMerchMarketPtActivityGoodsSpuDTO.Sign")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Sign extends BaseDTO {
        @ApiModelProperty(value = "活动ID")
        private String  activityId;
        private List<SignGoods> goodsAll;
        private List<SignGoodsSku> goodsSPUAll;
    }
    @Data
    @ApiModel("PCMerchMarketPtActivityGoodsSpuDTO.SignGoods")
    @AllArgsConstructor
    public static class SignGoods implements Serializable{
        @ApiModelProperty(value = "商品ID")
        private String  goodsId;
        @ApiModelProperty(value = "活动价")
        private BigDecimal activitySalePrice;


    }
    @Data
    @ApiModel("PCMerchMarketPtActivityGoodsSpuDTO.SignGoodsSku")
    @AllArgsConstructor
    public static class SignGoodsSku implements Serializable{
        @ApiModelProperty(value = "商品ID")
        private String goodsId ;

        @ApiModelProperty(value = "商家报名SPUID")
        private String goodsSpuItemId;

        @ApiModelProperty(value = "skuID")
        private String skuId;

        @ApiModelProperty(value = "活动价")
        private BigDecimal activitySalePrice;
    }

}
