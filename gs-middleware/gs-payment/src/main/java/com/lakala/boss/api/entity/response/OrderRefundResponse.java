package com.lakala.boss.api.entity.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-newboss-parent
 * @Package com.lakala.boss.api.entity.response
 * @Description: 标准快捷退款-OrderRefund
 * @date Date : 2019年11月20日 13:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class OrderRefundResponse extends BaseResponse {
    private static final long serialVersionUID = 4884822544936707820L;
    /**
     * 退款结果 SUCCESS：退款成功FAILED：退款失败
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
     * 退款金额 以分为单位
     */
    private String refundAmount;

}
