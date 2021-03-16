package com.lakala.boss.api.entity.request;

import com.lakala.boss.api.entity.response.StandardPQueryOrderResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-newboss-parent
 * @Package com.lakala.boss.api.entity.request
 * @Description: 支付订单查询-StandardPQueryOrder
 * @date Date : 2019年11月20日 14:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class StandardPQueryOrderRequest extends BaseRequest<StandardPQueryOrderResponse> {

    /**
     * 业务类型
     */
    private String service = "StandardPQueryOrder";

    /**
     * 商户订单号
     */
    private String orderId;

    @Override
    public Class<StandardPQueryOrderResponse> getResponseClass() {
        return StandardPQueryOrderResponse.class;
    }
}
