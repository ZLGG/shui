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
* @since 2020-12-09
*/
public abstract class PCMerchMarketMerchantGiftGoodsDTO implements Serializable {

    @Data
    @ApiModel("PCMerchMarketMerchantGiftGoodsDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty(value = "商家满赠活动ID",hidden = true)
        private String giftId;

        @ApiModelProperty(value = "商家ID",hidden = true)
        private String merchantId;

        @ApiModelProperty(value = "店铺ID",hidden = true)
        private String shopId;

        @ApiModelProperty("SPU_ID")
        private String goodsId;

        @ApiModelProperty("SKU_ID集合")
        private String skuIds;
    }

    @Data
    @ApiModel("PCMerchMarketMerchantGiftGoodsDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
