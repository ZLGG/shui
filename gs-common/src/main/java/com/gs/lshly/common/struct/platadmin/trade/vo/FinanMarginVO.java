package com.gs.lshly.common.struct.platadmin.trade.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author zhou
 * @Date 10:21 2020/9/18
 */
@Data
public class FinanMarginVO implements Serializable {

    private String id;

    private String merchantId;

    private String shopId;

    private BigDecimal paymentBalance;

    private BigDecimal paymentTotal;

    private Integer state;

}
