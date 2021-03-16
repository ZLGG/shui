package com.gs.lshly.common.struct.pos.body;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain = true)
public class OOnlineOrderPayment {
    /** 支付时间 */
    private Date payTime;
    /** 支付方式编码 */
    private String payMethodCode;
    /** 支付方式名称 */
    private String payMethodName;
    /** 支付金额 */
    private BigDecimal amount = BigDecimal.ZERO;
}
