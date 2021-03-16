package com.lakala.boss.api.entity.request;

import com.lakala.boss.api.entity.response.EntMergeOrderSearchResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.request
 * @Description: 订单查询接口请求参数
 * @date Date : 2019年10月12日 16:33
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class EntMergeOrderSearchRequest extends BaseRequest<EntMergeOrderSearchResponse> {

    /**
     * 业务类型
     */
    private String service = "EntMergeOrderSearch";

    /**
     * 商户订单号 需要查询的商户订单号
     */
    private String orderId;


    @Override
    public Class<EntMergeOrderSearchResponse> getResponseClass() {
        return EntMergeOrderSearchResponse.class;
    }
}
