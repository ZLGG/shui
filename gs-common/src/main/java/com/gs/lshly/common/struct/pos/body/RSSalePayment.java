package com.gs.lshly.common.struct.pos.body;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RSSalePayment implements Serializable {
    private static final long serialVersionUID = 8686075568161158645L;
    @ApiModelProperty("支付方式编码")
    private String payMethodCode;
    @ApiModelProperty("支付方式名称")
    private String payMethodName;
    @ApiModelProperty("支付金额")
    private BigDecimal amount;
    @ApiModelProperty("支付时间")
    private Date payTime;
    @ApiModelProperty("第三方支付的交易id")
    private String payTranId;
}