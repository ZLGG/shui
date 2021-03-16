package com.lakala.boss.api.entity.caps.response;

import lombok.Data;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-newboss-parent
 * @Package com.lakala.boss.api.entity.CapsResponse
 * @Description: 公共返回头
 * @date Date : 2019年12月28日 10:24
 */
@Data
@ToString
public class HeaderResponse {

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
     * 签名类型
     */
    private String signType;
    /**
     * 临时密钥
     */
    private String secretKey;
    /**
     * 接口标识
     */
    private String service;
    /**
     * 请求流水号
     */
    private String requestId;
    /**
     * 商户编号
     */
    private String merchantId;
    /**
     * 请求时间
     */
    private String requestTime;
}
