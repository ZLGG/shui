package com.lakala.boss.api.entity.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.response
 * @Description: B2C-预下单接口返回参数
 * @date Date : 2019年10月14日 10:54
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class NewEntMergePaymentResponse extends BaseResponse {
    private static final long serialVersionUID = -7472012796582663209L;

    /**
     * 流水号
     */
    private String tradeNo;

    /**
     * 商户订单号
     */
    private String orderId;

    /**
     * 商户编号
     */
    private String merchantId;

    /**
     * 银行跳转URL
     */
    private String bankUrl;

    /**
     * 银行跳转请求参数
     */
    private String epccGwMsg;

}
