package com.lakala.boss.api.entity.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.response
 * @Description: (这里用一句话描述这个类的作用)
 * @date Date : 2019年10月14日 09:42
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class SendMessageResponse extends BaseResponse {
    private static final long serialVersionUID = 5368847479680376280L;
    /**
     * 流水号
     */
    private String tradeNo;

    /**
     * 商户编号
     */
    private String merchantId;

}
