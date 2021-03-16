package com.lakala.boss.api.entity.request;

import com.lakala.boss.api.entity.response.OrderRefundResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-newboss-parent
 * @Package com.lakala.boss.api.entity.request
 * @Description: 标准快捷退款-OrderRefund
 * @date Date : 2019年11月20日 13:59
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class OrderRefundRequest extends BaseRequest<OrderRefundResponse> {


    /**
     * 业务类型
     */
    private String service = "OrderRefund";

    /**
     * 退款订单
     */
    private String orderId;

    /**
     * 退款金额 以分为单位
     */
    private String refundAmount;

    @Override
    public Class<OrderRefundResponse> getResponseClass() {
        return OrderRefundResponse.class;
    }
}
