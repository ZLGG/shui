package com.lakala.boss.api.entity.request;

import com.lakala.boss.api.entity.response.NewEntMergePaymentResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.request
 * @Description: (这里用一句话描述这个类的作用)
 * @date Date : 2019年10月14日 10:57
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class NewEntMergePaymentRequest extends BaseRequest<NewEntMergePaymentResponse> {

    private static final long serialVersionUID = -8924205194886275511L;

    /**
     * 业务类型
     */
    private String service = "NewEntMergePayment";

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
     * 商户的订单号，合作平台保证全局唯一
     */
    private String orderId;

    /**
     * 订单时间
     * 商户发起请求的时间; 格式：YYYYMMDDHHMISS
     */
    private String orderTime;

    /**
     * 订单总金额
     * 以分为单位,订单总金额需等于子订单金额和
     */
    private String totalAmount;

    /**
     * 交易币种
     * 默认 CNY
     */
    private String currency;

    /**
     * 原样返回的商户数据
     */
    private String backParam;

    /**
     * 分账方式
     * 1-子订单 2-按比例分账
     */
    private String splitType;

    /**
     * 银行代码
     */
    private String bankId;

    /**
     * 卡类型
     * 0 借记卡 1 贷记卡
     */
    private String cardType;

    /**
     * 订单有效期单位
     * 只能取以下枚举值 00 分 01 小时 02 日 03 月
     */
    private String validUnit;

    /**
     * 订单有效期数量
     */
    private String validNum;

    /**
     * 子订单明细
     */
    private List<OrderDetail> orderDetail;

    @Override
    public Class<NewEntMergePaymentResponse> getResponseClass() {
        return NewEntMergePaymentResponse.class;
    }
}
