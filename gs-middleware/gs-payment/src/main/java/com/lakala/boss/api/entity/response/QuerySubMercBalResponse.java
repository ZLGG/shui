package com.lakala.boss.api.entity.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.response
 * @Description: 余额查询接口返回参数
 * @date Date : 2019年10月14日 15:08
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class QuerySubMercBalResponse extends BaseResponse {

    private static final long serialVersionUID = 2044437253562732474L;

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
     * 子商户现金账户可用余额
     * 已分为单位
     */
    private String cashBal;

    /**
     * 子商户现金账户总余额
     * 已分为单位
     */
    private String tolStlBal;

}
