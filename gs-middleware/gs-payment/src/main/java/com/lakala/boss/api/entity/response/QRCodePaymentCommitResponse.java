package com.lakala.boss.api.entity.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.response
 * @Description: 微信/支付宝-支付方式确认返回参数
 * @date Date : 2019年10月14日 13:21
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class QRCodePaymentCommitResponse extends BaseResponse {
    private static final long serialVersionUID = 4528942707807171971L;

    /**
     * 流水号
     */
    private String tradeNo;

    /**
     * 商户编号
     */
    private String merchantId;

    /**
     * 通道请求参数
     */
    private String payInfo;

    /**
     * 订单token
     */
    private String token;

    /**
     * 预付单号-微信生成的预付单号
     */
    private String prePayId;
}
