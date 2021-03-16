package com.lakala.boss.api.entity.caps.response;

import lombok.Data;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-newboss-parent
 * @Package com.lakala.boss.api.entity.CapsResponse
 * @Description: (这里用一句话描述这个类的作用)
 * @date Date : 2019年12月28日 14:42
 */
@Data
@ToString
public class Caps004Response {
    /**
     * 商户订单号
     */
    private String orderId;
    /**
     * 平台订单号
     */
    private String paymentOrderNo;
    /**
     * 代付金额
     */
    private String capAmount;
    /**
     * 代付手续费
     */
    private String capFee;
    /**
     * 代付状态S-代付成功
     * F-代付失败
     * P-代付处理中
     */
    private String status;
    /**
     * 代付完成时间
     */
    private String capEndTime;
    /**
     * 预留字段1
     */
    private String backUpField1;
    /**
     * 预留字段2
     */
    private String backUpField2;
    /**
     * 预留字段3
     */
    private String backUpField3;
}
