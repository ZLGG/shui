package com.lakala.boss.api.entity.request;

import com.lakala.boss.api.entity.response.PaymentCommitResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.request
 * @Description: 支付确认接口请求参数
 * @date Date : 2019年10月14日 11:21
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class PaymentCommitRequest extends BaseRequest<PaymentCommitResponse> {

    /**
     * 业务类型
     */
    private String service = "PaymentCommit";

    /**
     * 客户号
     */
    private String purchaserId;

    /**
     * 商户下单订单号
     */
    private String orderId;

    /**
     * 下单后返回的订单token
     */
    private String token;

    /**
     * 支付方式
     * 01:协议支付有短信
     * 02 协议支付无短信
     * 03 认证支付
     * 04 商业委托支付
     */
    private String paymentType;

    /**
     * 短信验证码
     */
    private String validCode;

    /**
     * 用户银行卡签约号
     */
    private String bankBoundNo;

    /**
     * 持卡人姓名
     */
    private String capCrdName;

    /**
     * 证件类型
     * 00:身份证
     */
    private String idType;

    /**
     * 持卡人证件号
     */
    private String idNo;

    /**
     * 卡号
     */
    private String crdNo;

    /**
     * 卡有效期
     * 信用卡有效期 如:MMYY
     */
    private String crdExpDat;

    /**
     * 卡校验码
     * 信用卡校验码
     */
    private String cvn2;

    /**
     * 银行卡银行预留手机号
     */
    private String bnkPhone;

    @Override
    public Class<PaymentCommitResponse> getResponseClass() {
        return PaymentCommitResponse.class;
    }
}
