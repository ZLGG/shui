package com.gs.lshly.common.struct.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author lxus
 * @since 2021-2-5
 */
public abstract class CommonMarketVO implements Serializable {

    @Data
    @Accessors(chain = true)
    public static class ActiveVO implements Serializable {

        @ApiModelProperty("营销活动名称")
        private String activeName;

        @ApiModelProperty("营销优惠金额")
        private BigDecimal cutPrice;
    }

    @Data
    @Accessors(chain = true)
    public static class UserCardVO implements Serializable {
        @ApiModelProperty("优惠券Id")
        private String cardId;
        @ApiModelProperty("优惠券名称")
        private String cardName;
        @ApiModelProperty("优惠券优惠金额")
        private BigDecimal cutPrice;
    }

}
