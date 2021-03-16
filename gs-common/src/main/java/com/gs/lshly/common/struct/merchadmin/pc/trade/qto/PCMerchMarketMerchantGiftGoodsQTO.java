package com.gs.lshly.common.struct.merchadmin.pc.trade.qto;
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
* @since 2020-12-09
*/
public abstract class PCMerchMarketMerchantGiftGoodsQTO implements Serializable {

    @Data
    @ApiModel("PCMerchMarketMerchantGiftGoodsQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("商家满赠活动ID")
        private String giftId;

        @ApiModelProperty("商家ID")
        private String merchantId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("SPU_ID")
        private String goodsId;

        @ApiModelProperty("SKU_ID集合")
        private String skuIds;
    }
}
