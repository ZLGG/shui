package com.lakala.boss.api.entity.request;

import com.lakala.boss.api.entity.response.SendMessageResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.request
 * @Description: 短信下发接口
 * @date Date : 2019年10月14日 09:42
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class SendMessageRequest extends BaseRequest<SendMessageResponse> {

    /**
     * 业务类型
     */
    private String service = "SendMessage";

    /**
     * 客户号
     */
    private String purchaserId;

    /**
     * 短信类型 0:签约短信
     * 1:支付短信
     */
    private String msgType;

    /**
     * 支付方式 01:签约码支付
     * 02:认证支付
     * 当msgType为1时此项必传
     */
    private String paymentType;

    /**
     * 订单返回的token
     * 下单后返回的订单token,当msgType=1时必填
     */
    private String token;

    /**
     * 用户银行卡绑定号
     * 商户用户在支付系统银行卡签约号
     * paymentType为01时非空
     */
    private String bankBoundNo;

    /**
     * 持卡人姓名
     * msgType=0或者paymentType=02，不能空
     */
    private String capCrdName;

    /**
     * 证件类型
     */
    private String idType;

    /**
     * 持卡人证件号
     * 00:身份证
     * msgType=0或者paymentType=02，不能空
     */
    private String idNo;

    /**
     * 卡号
     * msgType=0或者paymentType=02，不能空
     */
    private String crdNo;

    /**
     * 卡有效期
     * msgType=0或者paymentType=02，且为信用卡时不能空 例如:MMYY
     */
    private String crdExpDat;

    /**
     * 卡校验码
     * msgType=0或者paymentType=02，且为信用卡时不能空
     */
    private String cvn2;

    /**
     * 卡预留手机号
     * msgType=0或者paymentType=02，不能空
     */
    private String bnkPhone;

    @Override
    public Class<SendMessageResponse> getResponseClass() {
        return SendMessageResponse.class;
    }
}
