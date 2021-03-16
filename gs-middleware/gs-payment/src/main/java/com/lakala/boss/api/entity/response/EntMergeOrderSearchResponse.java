package com.lakala.boss.api.entity.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.response
 * @Description: 订单查询接口返回参数
 * @date Date : 2019年10月12日 16:34
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class EntMergeOrderSearchResponse extends BaseResponse {

    private static final long serialVersionUID = 7958488588402411559L;

    /**
     * 商户编号
     */
    private String merchantId;

    /**
     * 系统返回的交易流水号
     */
    private String tradeNo;
    /**
     * 商户订单号 合作平台的订单号
     */
    private String orderId;

    /**
     * 订单时间  商户发起请求的时间，
     * 格式：YYYYMMDDHHMISS
     */
    private String orderTime;

    /**
     * 订单总金额 以分为单位
     */
    private String totalAmount;

    /**
     * 支付金额 本笔订单支付金额，以分为单位
     */
    private String payAmount;

    /**
     * 支付银行
     */
    private String bankAbbr;

    /**
     * 手续费金额 以分为单位
     */
    private String feeAmount;

    /**
     * 支付结果
     */
    private String status;

    /**
     * 失败原因 如果支付失败时，拿到明确失败原因，在此返回
     */
    private String failMsg;

    /**
     * 订单明细
     */
    private String orderDetails;

}
