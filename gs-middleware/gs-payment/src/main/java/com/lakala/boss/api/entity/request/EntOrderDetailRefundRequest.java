package com.lakala.boss.api.entity.request;

import com.lakala.boss.api.entity.response.EntOrderRefundResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.request
 * @Description: 退款接口请求参数
 * @date Date : 2019年10月14日 11:33
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class EntOrderDetailRefundRequest extends BaseRequest<EntOrderRefundResponse> {

    /**
     * 业务类型
     */
    private String service = "EntOrderDetailRefund";

    /**
     * 商户订单号
     * 需要退款的商户订单号
     */
    private String orderId;

    /**
     * 商户退款订单号
     */
    private  String refundOrderId;

    /**
     * 退款总金额
     * 以分为单位
     */
    private String refundTolAmount;

    /**
     * 子订单退款明细
     */
    private String refundDetail;

    @Override
    public Class<EntOrderRefundResponse> getResponseClass() {
        return EntOrderRefundResponse.class;
    }


}
