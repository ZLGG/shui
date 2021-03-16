package com.lakala.boss.api.entity.request;

import com.lakala.boss.api.entity.response.StandardPQueryRefundedResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-newboss-parent
 * @Package com.lakala.boss.api.entity.request
 * @Description: 退款查询 StandardPQueryRefunded
 * @date Date : 2019年11月20日 14:14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class StandardPQueryRefundedRequest extends BaseRequest<StandardPQueryRefundedResponse> {

    /**
     * 业务类型
     */
    private String service = "StandardPQueryRefunded";

    /**
     * 退款请求时上送的请求号
     */
    private String refundRequestId;


    @Override
    public Class<StandardPQueryRefundedResponse> getResponseClass() {
        return StandardPQueryRefundedResponse.class;
    }
}
