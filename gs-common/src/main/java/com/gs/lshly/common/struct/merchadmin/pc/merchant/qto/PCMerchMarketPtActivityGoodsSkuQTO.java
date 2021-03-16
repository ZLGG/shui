package com.gs.lshly.common.struct.merchadmin.pc.merchant.qto;
import com.gs.lshly.common.struct.BaseQTO;
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
public abstract class PCMerchMarketPtActivityGoodsSkuQTO implements Serializable {

    @Data
    @ApiModel("PCMerchMarketPtActivityGoodsSkuQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

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

        @ApiModelProperty("sku活动价")
        private Float activitySaleSkuPrice;
    }
}
