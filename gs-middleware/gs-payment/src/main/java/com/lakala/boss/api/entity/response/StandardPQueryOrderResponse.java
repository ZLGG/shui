package com.lakala.boss.api.entity.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-newboss-parent
 * @Package com.lakala.boss.api.entity.response
 * @Description: 支付订单查询-StandardPQueryOrder
 * @date Date : 2019年11月20日 14:36
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class StandardPQueryOrderResponse extends BaseResponse {

    private static final long serialVersionUID = -7872160191433333191L;

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
     * 订单时间
     */
    private String orderTime;

    /**
     * 订单总金额 以分为单位
     */
    private String totalAmount;

    /**
     * 支付金额 以分为单位
     */
    private String payAmount;

    /**
     * 支付银行
     */
    private String bankAbbr;

    /**
     * 手续费金额
     */
    private String feeAmount;
}
