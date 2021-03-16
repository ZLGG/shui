package com.lakala.boss.api.entity.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.response
 * @Description: 退款接口返回参数
 * @date Date : 2019年10月14日 11:31
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class EntOrderRefundResponse extends BaseResponse {
    private static final long serialVersionUID = -9202348872816143862L;

    /**
     * 退款结果 SUCCESS：退款成功 FAILED：退款失败
     */
    private String status;

    /**
     * 流水号
     */
    private String tradeNo;

    /**
     * 商户编号
     */
    private String merchantId;

    /**
     * 商户订单号
     */
    private String orderId;

    /**
     * 商户子订单号
     */
    private String detailOrderId;

    /**
     * 退款金额 以分为单位
     */
    private String refundAmount;

    /**
     * 原分账方商户编号
     */
    private String rcvMerchantId;

}
