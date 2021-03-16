package com.lakala.boss.api.entity.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.response
 * @Description: 子商户进件接口返回参数
 * @date Date : 2019年10月14日 13:42
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class MercRegisterResponse extends BaseResponse {
    private static final long serialVersionUID = -7923293159881690605L;

    /**
     * 开户状态 S-开户成功
     */
    private String status;

    /**
     * 请求号
     */
    private String requestId;

    /**
     * 商户编号
     */
    private String merchantId;

    /**
     * 请求时间
     */
    private String reqDtime;

    /**
     * 子商户编号
     */
    private String outMerchantId;

    /**
     * 商户名称
     */
    private String merchantName;
}
