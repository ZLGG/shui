package com.lakala.boss.api.entity.request;

import com.lakala.boss.api.entity.response.StandardQuickPaymentResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Project: lkl-newboss-parent
 * @Package: com.lakala.boss.api.entity.request
 * @Description: 快捷下单
 * @author: LXF
 * @date Date: 2019年11月20日 13:59
 * @version: V1.0.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class StandardQuickPaymentRequest extends BaseRequest<StandardQuickPaymentResponse>{

    /**
     * 业务类型
     */
    private String service;

    /**
     * 客户号
     */
    private String purchaserId;

    /**
     * 后台通知url
     */
    private String offlineNotifyUrl;

    /**
     * 前台通知url
     */
    private String pageNotifyUrl;

    /**
     * 合作商户展示名称
     */
    private String merchantName;

    /**
     * 订单号
     */
    private String orderId;

    /**
     * 订单时间
     */
    private String orderTime;

    /**
     * 订单总金额
     */
    private String totalAmount;

    /**
     * 交易币种
     */
    private String currency;

    /**
     * 原样返回的商户数据
     */
    private String backParam;

    /**
     * 订单有效期单位
     */
    private String validUnit;

    /**
     * 订单有效期数量
     */
    private String validNum;

    /**
     * 商品展示url
     */
    private String showUrl;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品编号
     */
    private String productId;

    /**
     * 商品描述
     */
    private String productDesc;

    @Override
    public Class<StandardQuickPaymentResponse> getResponseClass() {
        return StandardQuickPaymentResponse.class;
    }
}
