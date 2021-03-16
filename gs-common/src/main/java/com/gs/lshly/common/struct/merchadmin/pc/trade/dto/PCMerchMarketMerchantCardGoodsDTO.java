package com.gs.lshly.common.struct.merchadmin.pc.trade.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author zdf
* @since 2020-12-04
*/
public abstract class PCMerchMarketMerchantCardGoodsDTO implements Serializable {

    @Data
    @ApiModel("PCMerchMarketMerchantCardGoodsDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("商家优惠卷ID")
        private String cardId;

        @ApiModelProperty("商家ID")
        private String merchantId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("SPU_ID(商品ID)")
        private String goodsId;

        @ApiModelProperty("SKU_ID")
        private String skuId;
    }

    @Data
    @ApiModel("PCMerchMarketMerchantCardGoodsDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }
    @Data
    @ApiModel("PCMerchMarketMerchantCardGoodsDTO.GoodsInfo")
    @AllArgsConstructor
    public static class GoodsInfo implements Serializable {

        @ApiModelProperty("SPU_ID(商品ID)")
        private String goodsId;

        @ApiModelProperty("SKU_ID")
        private String skuId;
    }


}
