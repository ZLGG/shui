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
public class EntOrderRefundRequest extends BaseRequest<EntOrderRefundResponse> {

    /**
     * 业务类型
     */
    private String service = "EntOrderRefund";

    /**
     * 商户订单号
     * 需要退款的商户订单号
     */
    private String orderId;


    /**
     * 退款总金额
     *
     * */
    private String refundTolAmount;

    /**
     * 商户子订单号
     * 需要退款的子订单号，部分退款时必填
     */
    private String detailOrderId;

    /**
     * 退款金额
     * 以分为单位
     */
    private String detailRefundAmount;

    /**
     * 原分账方商户编号
     * 部分退款时必填
     */
    private String rcvMerchantId;

    @Override
    public Class<EntOrderRefundResponse> getResponseClass() {
        return EntOrderRefundResponse.class;
    }


}
