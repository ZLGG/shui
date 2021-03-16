package com.lakala.boss.api.entity.request;

import com.lakala.boss.api.entity.response.QRCodePaymentCommitResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.request
 * @Description: 微信/支付宝-支付方式确认请求参数
 * @date Date : 2019年10月14日 13:23
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class QRCodePaymentCommitRequest extends BaseRequest<QRCodePaymentCommitResponse> {

    /**
     * 业务类型
     */
    private String service = "QRCodePaymentCommit";

    /**
     * 商户下单时使用订单号
     */
    private String orderId;

    /**
     * 订单预下单创建时间
     */
    private String creDt;

    /**
     * 下单后返回的订单token
     */
    private String token;

    /**
     * 支付渠道类型
     * WECHAT: 微信钱包
     * ALIPAY:支付宝
     */
    private String payChlTyp;

    /**
     * 支付方式
     * JSAPI:微信公众号
     * WECHATAPP:APP支付
     * MINIAPP:小程序
     * WXNATIVE:微信扫码
     * ALINATIVE:支付宝扫码
     */
    private String tradeType;

    /**
     * appId
     * 商户获取微信的appid 支付方式为NATIVE时不传，其他方式必传
     */
    private String appId;

    /**
     * 用户授权标识
     * 商户通过用户授权获取
     * 支付方式为APP和NATIVE时不传，其他方式必传
     */
    private String openId;

    @Override
    public Class<QRCodePaymentCommitResponse> getResponseClass() {
        return QRCodePaymentCommitResponse.class;
    }
}
