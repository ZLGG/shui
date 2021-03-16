package com.lakala.boss.api.entity.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.response
 * @Description: 提现接口返回参数
 * @date Date : 2019年10月14日 15:23
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class WithdrawalCommitResponse extends BaseResponse {

    private static final long serialVersionUID = 3899158889203567201L;
    /**
     * 流水号
     */
    private String tradeNo;
    /**
     * 商户编号
     */
    private String merchantId;
    /**
     * 提现订单号 提现申请的商户侧订单号，每次请求保持全局唯一
     */
    private String wdcOrdNo;
    /**
     * 提现受理状态
     * S-受理成功
     * F-受理失败
     */
    private String status;

}
