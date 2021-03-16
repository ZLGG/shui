package com.lakala.boss.api.entity.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.response
 * @Description: 图片上传通知接口返回参数
 * @date Date : 2019年10月14日 15:03
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class MercPicInfoResponse extends BaseResponse {

    private static final long serialVersionUID = -3564013202922984078L;

    /**
     * 请求号
     */
    private String requestId;

    /**
     * 商户编号
     */
    private String merchantId;
}
