package com.lakala.boss.api.entity.request;

import com.lakala.boss.api.entity.response.WithdrawalCommitResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.request
 * @Description: 提现接口请求参数
 * @date Date : 2019年10月14日 15:24
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class WithdrawalCommitRequest extends BaseRequest<WithdrawalCommitResponse> {

    /**
     * 业务类型
     */
    private String service = "withdrawalCommit";

    /**
     * 子商户编号
     */
    private String subMerchantId;

    /**
     * 提现申请金额 以分为单位
     */
    private String wdcAplAmt;

    @Override
    public Class<WithdrawalCommitResponse> getResponseClass() {
        return WithdrawalCommitResponse.class;
    }
}
