package com.gs.lshly.common.struct.merchadmin.pc.trade.vo;
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
* @since 2020-12-10
*/
public abstract class PCMerchMarketMerchantGroupbuyGoodsVO implements Serializable {

    @Data
    @ApiModel("PCMerchMarketMerchantGroupbuyGoodsVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("商家团购活动ID")
        private String groupbuyId;


        @ApiModelProperty("商家ID")
        private String merchantId;


        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("SPU_ID")
        private String goodsId;


        @ApiModelProperty("SKU_ID")
        private String skuId;


        @ApiModelProperty("原价")
        private BigDecimal originalPrice;


        @ApiModelProperty("活动价")
        private BigDecimal groupbuyPrice;




    }

    @Data
    @ApiModel("PCMerchMarketMerchantGroupbuyGoodsVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
