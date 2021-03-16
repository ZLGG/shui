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
public abstract class PCMerchMarketMerchantGiftGoodsGiveVO implements Serializable {

    @Data
    @ApiModel("PCMerchMarketMerchantGiftGoodsGiveVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("商家满赠活动ID")
        private String giftId;


        @ApiModelProperty("商家ID")
        private String merchantId;


        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("SKU_ID")
        private String skuId;

        @ApiModelProperty("赠品数量")
        private Integer total;




    }

    @Data
    @ApiModel("PCMerchMarketMerchantGiftGoodsGiveVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
