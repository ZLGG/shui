package com.gs.lshly.common.struct.merchadmin.pc.merchant.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
* @author zdf
* @since 2020-12-02
*/
public abstract class PCMerchMarketPtActivityGoodsSkuDTO implements Serializable {

    @Data
    @ApiModel("PCMerchMarketPtActivityGoodsSkuDTO.ETO")
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

        @ApiModelProperty("商家报名商品SPU项ID")
        private String goodsSpuItemId;

        @ApiModelProperty("商品ID")
        private String goodsId;

        @ApiModelProperty("skuID")
        private String skuId;

        @ApiModelProperty("sku活动价")
        private BigDecimal activitySaleSkuPrice;
    }

    @Data
    @ApiModel("PCMerchMarketPtActivityGoodsSkuDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
