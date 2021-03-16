package com.lakala.boss.api.entity.request;

import com.lakala.boss.api.entity.response.RefundDetailSeachResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-newboss-parent
 * @Package com.lakala.boss.api.entity.request
 * @Description: (这里用一句话描述这个类的作用)
 * @date Date : 2019年11月19日 14:02
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class RefundDetailSeachRequest extends BaseRequest<RefundDetailSeachResponse> {

    /**
     * 业务类型
     */
    private String service = "RefundDetailSeach";

    /**
     * 商户退款订单号
     */
    private String refundOrderId;

    @Override
    public Class<RefundDetailSeachResponse> getResponseClass() {
        return RefundDetailSeachResponse.class;
    }
}
