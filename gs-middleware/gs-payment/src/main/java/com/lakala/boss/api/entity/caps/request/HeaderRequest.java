package com.lakala.boss.api.entity.caps.request;

import lombok.Data;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-newboss-parent
 * @Package com.lakala.boss.api.entity.CapsRequest
 * @Description: 公共请求头
 * @date Date : 2019年12月28日 10:24
 */
@Data
@ToString
public class HeaderRequest {
    /**
     * 字符集
     */
    private String charset="01";
    /**
     * 版本号
     */
    private String version="1.0";
    /**
     * 商户签名串
     */
    private String merchantSign;
    /**
     * 加签类型
     */
    private String signType="RSA";
    /**
     * 临时密钥
     */
    private String secretKey;
    /**
     * 接口标识
     */
    private String service;
    /**
     * 客户端IP
     */
    private String clientIp="10.177.215.129";
    /**
     * 通讯商户编号
     */
    private String merchantId;
    /**
     * 请求流水号
     */
    private String requestId;
    /**
     * 请求时间
     */
    private String requestTime;

}
