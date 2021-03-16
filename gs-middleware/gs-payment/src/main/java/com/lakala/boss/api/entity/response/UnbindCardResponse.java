package com.lakala.boss.api.entity.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.response
 * @Description: 协议解约接口返回参数
 * @date Date : 2019年10月14日 10:45
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class UnbindCardResponse extends BaseResponse {

    private static final long serialVersionUID = 5314566513964787593L;

    /**
     * 流水号
     */
    private String tradeNo;

    /**
     * 商户编号
     */
    private String merchantId;

}
