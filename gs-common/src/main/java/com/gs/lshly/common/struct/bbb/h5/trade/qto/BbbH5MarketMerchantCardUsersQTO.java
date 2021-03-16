package com.gs.lshly.common.struct.bbb.h5.trade.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author zdf
* @since 2021-01-08
*/
public abstract class BbbH5MarketMerchantCardUsersQTO implements Serializable {

    @Data
    @ApiModel("BbbH5MarketMerchantCardUsersQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {
        @ApiModelProperty("状态[10=未使用 20=已使用 30=已过期]")
        private Integer state;

        @ApiModelProperty("过期时间[10=升序 20=降序]")
        private Integer expirationTime;

        @ApiModelProperty("优惠金额[10=升序 20=降序]")
        private Integer preferentialAmount;

    }
}
