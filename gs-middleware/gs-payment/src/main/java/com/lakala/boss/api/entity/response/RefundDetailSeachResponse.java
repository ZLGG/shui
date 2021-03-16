package com.lakala.boss.api.entity.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-newboss-parent
 * @Package com.lakala.boss.api.entity.response
 * @Description: (这里用一句话描述这个类的作用)
 * @date Date : 2019年11月19日 14:02
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class RefundDetailSeachResponse extends BaseResponse {
    private static final long serialVersionUID = -6168131795586337992L;

    /**
     * 退款结果
     * SUCCESS：退款成功 FAILED：退款失败
     */
    private String status;

    /**
     * 商户编号
     */
    private String merchantId;

    /**
     * 商户订单号
     */
    private String orderId;

    /**
     * 退款总金额
     */
    private String refundTolAmount;

    /**
     * 子订单退款明细
     */
    private String refundDetail;

}
