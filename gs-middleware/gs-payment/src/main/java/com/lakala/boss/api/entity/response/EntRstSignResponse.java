package com.lakala.boss.api.entity.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.response
 * @Description: 商业委托短信下发接口
 * @date Date : 2019年10月14日 09:53
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class EntRstSignResponse extends BaseResponse {
    private static final long serialVersionUID = -1620345802782995724L;

    /**
     * 流水号
     */
    private String tradeNo;

    /**
     * 商户编号
     */
    private String merchantId;

    /**
     * 请求号
     */
    private String requestId;

    /**
     * 网关签约URL
     */
    private String gatewayUrl;
}
