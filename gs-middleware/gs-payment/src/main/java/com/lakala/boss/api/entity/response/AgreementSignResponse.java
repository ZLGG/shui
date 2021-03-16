package com.lakala.boss.api.entity.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.response
 * @Description: 签约接口返回参数
 * @date Date : 2019年10月14日 10:20
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class AgreementSignResponse extends BaseResponse {

    private static final long serialVersionUID = -2378432967178644165L;

    /**
     * 流水号
     */
    private String tradeNo;

    /**
     * 商户编号
     */
    private String merchantId;

    /**
     * 用户银行卡绑定号
     */
    private String bankBoundNo;

}
