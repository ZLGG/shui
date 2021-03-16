package com.lakala.boss.api.entity.request;

import com.lakala.boss.api.entity.response.BaseResponse;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.request
 * @Description: 公共请求字段
 * @date Date : 2019年10月12日 14:26
 */
@Data
@ToString
public abstract class BaseRequest<T extends BaseResponse> implements Serializable {

    private static final long serialVersionUID = -5828316657012081518L;

    /**
     * 字符集
     */
    private String charset = "00";
    /**
     * 接口版本
     */
    private String version = "1.0";
    /**
     * 商户签名
     */
    private String merchantSign;
    /**
     * 签名方式
     */
    private String signType = "RSA";
    /**
     * 临时密钥
     */
    private String secretKey;
    /**
     * 客户端ip
     */
    private String clientIP;
    /**
     * 商户编号
     */
    private String merchantId;
    /**
     * 请求号
     */
    private String requestId;

    public abstract Class<T> getResponseClass();

}
