package com.lakala.boss.api.entity.request;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
public class RefundDetail {

    /**
     * 子订单号
     * */
    private String detailOrderId;

    /**
            * 子订单号
     * */
    private String detailRefundAmount;

    /**
            * 子订单号
     * */
    private String rcvMerchantId;
}
