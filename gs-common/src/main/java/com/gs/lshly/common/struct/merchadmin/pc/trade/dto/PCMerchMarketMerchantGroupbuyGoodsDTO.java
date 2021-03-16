package com.gs.lshly.common.struct.merchadmin.pc.trade.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author zdf
* @since 2020-12-10
*/
public abstract class PCMerchMarketMerchantGroupbuyGoodsDTO implements Serializable {

    @Data
    @ApiModel("PCMerchMarketMerchantGroupbuyGoodsDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty(value = "商家团购活动ID",hidden = true)
        private String groupbuyId;

        @ApiModelProperty(value = "商家ID",hidden = true)
        private String merchantId;

        @ApiModelProperty(value = "店铺ID",hidden = true)
        private String shopId;

        @ApiModelProperty("SPU_ID")
        private String goodsId;

        @ApiModelProperty("SKU_ID")
        private String skuId;

        @ApiModelProperty("原价")
        private BigDecimal originalPrice;

        @ApiModelProperty("活动价")
        private BigDecimal groupbuyPrice;

        @ApiModelProperty("sku商品信息")
        private List<skuGoodsList> skuGoodsLists;

        @Data
        @ApiModel("PCMerchMarketMerchantGroupbuyGoodsDTO.ETO.skuGoodsList")
        @AllArgsConstructor
        @NoArgsConstructor
        public static class skuGoodsList implements Serializable{

            @ApiModelProperty("skuId")
            private String skuId;

            @ApiModelProperty("sku活动价")
            private BigDecimal groupbuySaleSkuPrice;
        }
    }

    @Data
    @ApiModel("PCMerchMarketMerchantGroupbuyGoodsDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("PCMerchMarketMerchantGroupbuyGoodsDTO.Check")
    @AllArgsConstructor
    public static class Check extends BaseDTO {

        @ApiModelProperty(value = "商家团购id")
        private String id;
        //ActivitySignEnum
        @ApiModelProperty(value = "审核方式枚举类型[10=审核通过 30=审核驳回]")
        private Integer pattern;
        @ApiModelProperty(value = "驳回原因")
        private String revokeWhy ;
    }

}
