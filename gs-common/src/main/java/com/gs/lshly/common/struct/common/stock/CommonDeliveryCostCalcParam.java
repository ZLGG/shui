package com.gs.lshly.common.struct.common.stock;

import com.gs.lshly.common.enums.stock.StockDeliveryCostCalcWayEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 运费计算参数
 * @author lxus
 * @since 2020-11-18
 */
@Data
@Accessors(chain = true)
public class CommonDeliveryCostCalcParam implements Serializable {

    @ApiModelProperty("运费计算方式")
    private StockDeliveryCostCalcWayEnum calcWay;

    @ApiModelProperty("固定费用")
    private BigDecimal customizeCost;

    @ApiModelProperty("计件/计重-计算参数")
    private QuantityParam quantityParam;

    @ApiModelProperty("匹配到的价格区间-计算参数")
    private BigDecimal stepPriceParam;

    /**
     * 计件/计重-计算参数
     * @author lxus
     * @since 2020-11-18
     */
    @Data
    @Accessors(chain = true)
    public static class QuantityParam implements Serializable {

        @ApiModelProperty("首(件/重)")
        private BigDecimal first;

        @ApiModelProperty("首价(件/重)")
        private BigDecimal firstPrice;

        @ApiModelProperty("续(件/重)")
        private BigDecimal increase;

        @ApiModelProperty("续价(件/重)")
        private BigDecimal increasePrice;

    }
    /**
     * 阶梯价-计算参数
     * @author lxus
     * @since 2020-11-18
     */
    @Data
    @Accessors(chain = true)
    public static class StepPriceParam implements Serializable {

        @ApiModelProperty("最小值(从0开始，按该值排序)")
        private BigDecimal min;

        @ApiModelProperty("最大值(-1则为无穷大)")
        private BigDecimal max;

        @ApiModelProperty("价格")
        private BigDecimal price;

    }


}
