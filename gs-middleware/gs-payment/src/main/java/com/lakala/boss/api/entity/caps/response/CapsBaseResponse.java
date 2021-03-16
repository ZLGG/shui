package com.lakala.boss.api.entity.caps.response;

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
public abstract class CapsBaseResponse implements Serializable {

    private static final long serialVersionUID = -4522648077796196609L;
    /***
     * 响应吗
     */
    private String returnCode;
    /***
     * 响应信息
     */
    private String returnMessage;
}
