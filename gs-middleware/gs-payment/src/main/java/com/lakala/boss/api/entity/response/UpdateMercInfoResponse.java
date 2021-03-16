package com.lakala.boss.api.entity.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.response
 * @Description: 进件信息修改接口返回参数
 * @date Date : 2019年10月14日 14:37
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class UpdateMercInfoResponse extends BaseResponse {

    private static final long serialVersionUID = -4819892161200690269L;

    /**
     * 修改状态
     * S-修改成功
     * F-修改失败
     */
    private String status;

    /**
     * 请求号
     */
    private String requestId;

    /**
     * 商户编号
     */
    private String merchantId;

    /**
     * 请求时间 YYYYMMDDHHMISS
     */
    private String reqDtime;


}
