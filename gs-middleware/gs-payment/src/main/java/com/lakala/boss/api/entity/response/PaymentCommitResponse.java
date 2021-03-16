package com.lakala.boss.api.entity.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.response
 * @Description: 支付确认接口返回参数
 * @date Date : 2019年10月14日 11:19
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class PaymentCommitResponse extends BaseResponse {
    private static final long serialVersionUID = 5610001451564203286L;

    /**
     * 系统返回的交易流水号
     */
    private String tradeNo;

    /**
     * 商户编号
     */
    private String merchantId;

    /**
     * 交易结果 S-交易成功 F-交易失败 P-交易处理中
     */
    private String status;

    /**
     * 商户下单订单号
     */
    private String orderId;

    /**
     * 支付时间
     * 用户完成支付的时间,  格式：YYYYMMDDHHMISS
     */
    private String payTime;

    /**
     * 会计日期
     * 系统会计日期，格式：YYYYMMDD
     */
    private String acDate;
}
