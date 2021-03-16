package com.lakala.boss.api.entity.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author: LXF
 * @version: V1.0.0
 * @Project: lkl_boss_sdk
 * @Package: com.lakala.boss.api.response
 * @Description:
 * @date: 2019-10-11 下午2:37:18
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class EntOffLinePaymentResponse extends BaseResponse {

    private static final long serialVersionUID = 4467923613472241322L;

    /**
     *
     */
    private String tradeNo;
    /**
     *
     */
    private String merchantId;
    /**
     *
     */
    private String orderId;
    /**
     *
     */
    private String token;

    private String tradeType;

    private String xcxUsername;

    private String xcxReqpath;

    private String h5JumpUrl;

}
