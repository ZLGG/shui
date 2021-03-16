package com.gs.lshly.common.struct.merchadmin.pc.merchant.qto;
import com.gs.lshly.common.struct.BaseQTO;
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
* @since 2020-12-01
*/
public abstract class PCMerchMarketPtActivityGoodsSpuQTO implements Serializable {

    @Data
    @ApiModel("PCMerchMarketPtActivityGoodsSpuQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("活动ID")
        private String activityId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String merchantId;

        @ApiModelProperty("活动名称")
        private String name;

        @ApiModelProperty("标签")
        private String label;
        /**
         * sku活动价
         */
        private BigDecimal activitySalePrice;
        @ApiModelProperty("描述")
        private String activityDescribe;

        @ApiModelProperty("商品ID")
        private String goodsId;
    }
}
