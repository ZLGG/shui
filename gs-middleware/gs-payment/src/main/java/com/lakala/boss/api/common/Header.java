package com.lakala.boss.api.common;

import lombok.*;

import java.io.Serializable;

/**
 * @author : Jian Jang
 * @version V1.0.0
 * @Project: caps
 * @Package com.lakala.boss.api.common
 * @Description: 请求头公共类
 * @date Date : 2019年12月25日 10:40
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Header  implements Serializable {

    private static final long serialVersionUID = 1781995591611739814L;
    /**
     * 字符集
     */
    private String charset;
    /***
     * 接口版本
     */
    private String version;

    /***
     * 请求号
     */
    private String requestId;
    /***
     * 商户签名
     */
    private String merchantSign;
    /***
     * 签名方式
     */
    private String signType;
    /***
     * 业务类型
     */
    private String service;
    /***
     * 客户端IP
     */
    private String clientIp;
    /***
     * 通讯商户编号
     */
    private String merchantId;
    /***
     * 请求时间
     */
    private String requestTime;
}
