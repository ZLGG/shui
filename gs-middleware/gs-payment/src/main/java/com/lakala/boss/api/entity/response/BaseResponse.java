package com.lakala.boss.api.entity.response;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.response
 * @Description: 公共返回信息
 * @date Date : 2019年10月12日 14:27
 */
@Data
@ToString
public abstract class BaseResponse implements Serializable {
    private static final long serialVersionUID = 2744960397799321310L;

    /**
     * 临时密钥
     * 敏感字段加密时使用的密钥,如果报文中有需要加密的敏感字段，此字段必传
     */
    private String secretKey;

    private String returnCode;

    private String returnMessage;
    /**
     * 接口调用结果
     */
    private boolean isSuccess = false;
}
