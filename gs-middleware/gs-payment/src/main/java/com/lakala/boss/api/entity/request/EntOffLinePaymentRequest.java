package com.lakala.boss.api.entity.request;

import com.lakala.boss.api.entity.response.EntOffLinePaymentResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 预下单接口
 *
 * @author: LXF
 * @version: V1.0.0
 * @Project: lkl_boss_sdk
 * @Package: com.lakala.boss.api.request
 * @Description: 调用该接口进行下单，下单成功后，接口返回订单token，作为后续支付依据。
 * @date: 2019-10-11 下午2:37:35
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class EntOffLinePaymentRequest extends BaseRequest<EntOffLinePaymentResponse> {

    private static final long serialVersionUID = -7036036112538922912L;

    /**
     * 业务类型
     */
    private String service = "EntOffLinePayment";
    /**
     * 后台通知url
     */
    private String offlineNotifyUrl;
    /**
     * 前台通知url
     */
    private String pageNotifyUrl;
    /**
     * 客户号
     */
    private String purchaserId;
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
     * 分账方式
     */
    private String splitType;
    /**
     * 订单有效期单位
     */
    private String validUnit;
    /**
     * 订单有效期数量
     */
    private String validNum;

    /**
     * 支付方式
     */
    private String tradeType;
    /**
     * 子订单明细
     */
    private List<OrderDetail> orderDetail;

    @Override
    public Class<EntOffLinePaymentResponse> getResponseClass() {
        return EntOffLinePaymentResponse.class;
    }

}
