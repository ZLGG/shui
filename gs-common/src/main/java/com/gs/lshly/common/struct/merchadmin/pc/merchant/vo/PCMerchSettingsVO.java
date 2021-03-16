package com.gs.lshly.common.struct.merchadmin.pc.merchant.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author lxus
* @since 2020-10-30
*/
public abstract class PCMerchSettingsVO implements Serializable {

    @Data
    @ApiModel("店铺配送方式-页面显示对象")
    @Accessors(chain = true)
    public static class DeliveryStyleVO implements Serializable {

        @ApiModelProperty("支持的配送方式(多选)([10=快递 20=自提 30=门店配送])")
        private List<Integer> deliveryTypes;

        @ApiModelProperty("收费方式[10=自定义费用  20=按重量 30=按件数]")
        private Integer shopBillType;

        @ApiModelProperty("自定义配送费用")
        private BigDecimal shopCustomizePrice;

        @ApiModelProperty("配送范围（公里）")
        private Integer shopRanges;

        @ApiModelProperty("首件")
        private Integer shopFirstQuantity;

        @ApiModelProperty("首件费")
        private BigDecimal shopFirstQuantityPrice;

        @ApiModelProperty("续件")
        private Integer shopIncreaseQuantity;

        @ApiModelProperty("续件费")
        private BigDecimal shopIncreaseQuantityPrice;

        @ApiModelProperty("首重")
        private Integer shopFirstWeight;

        @ApiModelProperty("首重费")
        private BigDecimal shopFirstWeightPrice;

        @ApiModelProperty("续重")
        private Integer shopIncreaseWeight;

        @ApiModelProperty("续重费")
        private BigDecimal shopIncreaseWeightPrice;

    }
}
