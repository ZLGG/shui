package com.lakala.boss.api.entity.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-newboss-parent
 * @Package com.lakala.boss.api.entity.response
 * @Description: 支付退款查询-StandardPQueryRefunded
 * @date Date : 2019年11月20日 14:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class StandardPQueryRefundedResponse extends BaseResponse {
    private static final long serialVersionUID = -3520315297783142917L;

    /**
     * 退款结果 SUCCESS：退款成功FAILED：退款失败 PROCESS 处理中
     */
    private String status;

    /**
     * 商户编号
     */
    private String merchantId;

    /**
     * 退款请求号
     */
    private String refundRequestId;

    /**
     * 退款原订单号
     */
    private String orderId;

    /**
     * 退款金额，以分为单位
     */
    private String refundAmount;
}
