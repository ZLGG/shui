package com.gs.lshly.common.struct.merchadmin.pc.commodity.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author Starry
* @since 2020-10-27
*/
public abstract class PCMerchGoodsStockQTO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsStockQTO.SkuAlarmQTO")
    @Accessors(chain = true)
    public static class SkuAlarmQTO extends BaseQTO {

        @ApiModelProperty("店铺id")
        private String shopId;

        @ApiModelProperty("商家id")
        private String merchantId;
    }

}
