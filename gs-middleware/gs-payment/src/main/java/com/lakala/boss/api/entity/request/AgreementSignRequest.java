package com.lakala.boss.api.entity.request;

import com.lakala.boss.api.entity.response.AgreementSignResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.request
 * @Description: 签约接口请求参数
 * @date Date : 2019年10月14日 10:20
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class AgreementSignRequest extends BaseRequest<AgreementSignResponse> {

    /**
     * 业务类型
     */
    private String service = "AgreementSign";

    /**
     * 客户端ip
     */
    private String clientIP;

    /**
     * 请求号
     */
    private String requestId;

    /**
     * 客户号
     */
    private String purchaserId;

    /**
     * 短信验证码
     */
    private String validCode;

    /**
     * 短信下发请求号
     */
    private String smsRequestId;

    @Override
    public Class<AgreementSignResponse> getResponseClass() {
        return AgreementSignResponse.class;
    }
}
