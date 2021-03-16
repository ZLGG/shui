package com.gs.lshly.common.struct.merchadmin.pc.trade.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author zdf
* @since 2020-12-09
*/
public abstract class PCMerchMarketMerchantDiscountGoodsVO implements Serializable {

    @Data
    @ApiModel("PCMerchMarketMerchantDiscountGoodsVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("商家满折活动ID")
        private String discountId;


        @ApiModelProperty("商家ID")
        private String merchantId;


        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("SPU_ID")
        private String goodsId;


        @ApiModelProperty("SKU_ID集合")
        private String skuIds;




    }

    @Data
    @ApiModel("PCMerchMarketMerchantDiscountGoodsVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
