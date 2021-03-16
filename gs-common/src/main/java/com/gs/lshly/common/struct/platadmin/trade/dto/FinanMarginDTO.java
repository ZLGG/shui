package com.gs.lshly.common.struct.platadmin.trade.dto;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author lxus
 * @since 2020/09/14
 */
public abstract class FinanMarginDTO implements Serializable {

    @Data
    public static class QTO extends BaseQTO {

    }

    @Data
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        @ApiModelProperty("管理平台用户id")
        private String id;
    }

    @Data
    public static class ETO extends BaseDTO {

        private String id;

        private String merchantId;

        private BigDecimal paymentBalance;

        private BigDecimal paymentTotal;

        private String shopId;

    }

}
