package com.lakala.boss.api.entity.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.response
 * @Description: 协议查询接口返回参数
 * @date Date : 2019年10月14日 10:26
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class QueryCardBindResponse extends BaseResponse {

    private static final long serialVersionUID = -4926983201842925801L;
    /**
     * 商户编号
     */
    private String merchantId;
    /**
     * 绑定银行卡信息
     */
    private String cards;
}
