package com.lakala.boss.api.entity.notify;

import lombok.Data;
import lombok.ToString;

/**
 * @Project: boss-sdk
 * @Package: com.lakala.boss.api.entity.notify
 * @Description: 前台通知
 * @author: LXF
 * @date Date: 2019年10月29日 10:28
 * @version: V1.0.0
 */
@Data
@ToString
public class EntMergePageResultNotify {

    /**
     * 字符集
     */
    private String charset;
    /**
     * 版本号
     */
    private String version;
    /**
     * 服务器证书
     */
    private String serverCert;
    /**
     * 服务器签名
     */
    private String serverSign;
    /**
     * 签名方式
     */
    private String signType;
    /**
     * 临时密钥
     */
    private String secretKey;
    /**
     * 接口类型
     */
    private String service;
    /**
     * 支付结果
     */
    private String status;
    /**
     * 返回码
     */
    private String returnCode;
    /**
     * 返回码描述信息
     */
    private String returnMessage;
    /**
     * 流水号
     */
    private String tradeNo;
    /**
     * 商户编号
     */
    private String merchantId;
    /**
     * 商户订单号
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
     * 支付金额
     */
    private String payAmount;
    /**
     * 支付时间
     */
    private String payTime;
    /**
     * 会计日期
     */
    private String acDate;
    /**
     * 费用
     */
    private String fee;
    /**
     * 原样返回的商户数据
     */
    private String backParam;
    /**
     * 支付银行
     */
    private String bankAbbr;
    /**
     * 失败原因
     */
    private String failMsg;

    /**
     * 子订单明细
     */
    private String orderDetail;
}
