package com.lakala.boss.api.entity.caps.body;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 代付结果查询请求
 * @author liu_dd
 * @date 2019/12/26 4:44 下午
 * @version 1.0.0
 */
@Data
@Builder
@ToString
public class QueryCapsRealPayResultRequestBody implements Serializable {

    private static final long serialVersionUID = 31484811115976992L;
    /***
     * 商户订单号
     */
    private String orderId;
    /**
     * 支付订单号
     */
    private String paymentOrderNo;
    /**
     * 订单日期
     */
    private String orderDate;

}
