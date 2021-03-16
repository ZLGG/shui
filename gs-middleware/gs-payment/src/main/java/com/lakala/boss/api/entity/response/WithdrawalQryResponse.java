package com.lakala.boss.api.entity.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.response
 * @Description: 提现查询接口返回参数
 * @date Date : 2019年10月14日 15:28
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class WithdrawalQryResponse extends BaseResponse {

    private static final long serialVersionUID = -3001919952563498920L;

    /**
     * 流水号
     */
    private String tradeNo;

    /**
     * 商户编号
     */
    private String merchantId;

    /**
     * 子商户编号
     */
    private String subMerchantId;

    /**
     * 提现申请金额 以分为单位
     */
    private String wdcAplAmt;

    /**
     * 提现状态
     *  S-提现成功 P-提现处理中 F-提现失败
     */
    private String status;

    /**
     * 到账银行帐号
     */
    private String capCrdNo;

    /**
     * 到账银行户名
     */
    private String capCrdNm;

}
