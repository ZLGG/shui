package com.lakala.boss.api.entity.request;

import lombok.Data;
import lombok.ToString;

/**
 * 退款子订单明细
 *
 * @author: L.T.P
 * @version: V1.0.0
 * @Project: lkl_boss_sdk
 * @Package: com.lakala.boss.api.request
 * @Description: 退款子订单明细
 * @date: 2019-10-11 下午2:43:05
 */
@Data
@ToString
public class RefundSubOrderDetail {

    /**
     * 商户子订单号
     */
    private String detailOrderId;

    /**
     * 子订单退款金额
     * 以分为单位
     */
    private String detailRefundAmount;

    /**
     * 原分账方商户编号
     */
    private String rcvMerchantId;

}
