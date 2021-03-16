package com.lakala.boss.api.entity.notify;

import com.lakala.boss.api.entity.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Project: lkl-newboss-parent
 * @Package: com.lakala.boss.api.entity.request
 * @Description: 标准支付后台通知返回参数
 * @author: LXF
 * @date Date: 2019年11月20日 13:59
 * @version: V1.0.0
 */
@Data
@ToString
public class StandardAPBackNotify {

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
     * 返回码
     */
    private String returnCode;
    /**
     * 返回码描述信息
     */
    private String returnMessage;
    /**
     * 支付结果
     */
    private String status;
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
     * 支付银行
     */
    private String bankAbbr;
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
     * 失败原因
     */
    private String failMsg;
}
