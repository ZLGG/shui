package com.lakala.boss.api.entity.request;

import com.lakala.boss.api.entity.response.WithdrawalQryResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.request
 * @Description: 提现查询接口请求参数
 * @date Date : 2019年10月14日 15:29
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class WithdrawalQryRequest extends BaseRequest<WithdrawalQryResponse> {

    /**
     * 业务类型
     */
    private String service="withdrawalQry";

    /**
     * 提现订单号
     */
    private String wdcOrdNo;

    @Override
    public Class<WithdrawalQryResponse> getResponseClass() {
        return WithdrawalQryResponse.class;
    }
}
