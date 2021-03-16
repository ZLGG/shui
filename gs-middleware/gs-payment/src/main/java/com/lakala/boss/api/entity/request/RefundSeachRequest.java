package com.lakala.boss.api.entity.request;

import com.lakala.boss.api.entity.response.RefundSeachResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.request
 * @Description:  退款查询接口请求参数
 * @date Date : 2019年10月14日 13:16
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class RefundSeachRequest extends BaseRequest<RefundSeachResponse>{

    /**
     * 业务类型
     */
    private String service="RefundSeach";

    /**
     * 退款请求号
     * 退款请求时上送的请求号
     */
    private String refundRequestId;


    @Override
    public Class<RefundSeachResponse> getResponseClass() {
        return RefundSeachResponse.class;
    }
}
