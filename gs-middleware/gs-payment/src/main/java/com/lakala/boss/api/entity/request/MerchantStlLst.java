package com.lakala.boss.api.entity.request;

import lombok.Data;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.request
 * @Description: 子商户结算信息开始
 * @date Date : 2019年10月14日 14:06
 */
@Data
@ToString
public class MerchantStlLst {

    /**
     * 子商户结算账号公私标志
     * 0-对公
     * 1-对私
     */
    private String stlSign;

    /**
     * 子商户自结算标志
     * N-不允许
     * Y-允许
     */
    private String selfStlFlg;

    /**
     * 银行代码 银行简称 如ICBC
     */
    private String bankName;

    /**
     * 开户行名称
     */
    private String stlBankName;

    /**
     * 支行归属省
     */
    private String opnBnkProv;

    /**
     * 支行归属市
     */
    private String opnBnkCity;

    /**
     * 联行行号
     */
    private String lBnkNo;

    /**
     * 银行帐号
     */
    private String stlOac;

    /**
     * 银行开户名称
     */
    private String bnkOpnName;

    /**
     * 商户结算类型
     * 1-结算到账户
     * 2-结算到银行账户
     */
    private String stlCls;

    /**
     * 结算周期
     * 0-日 1-周 2-旬 3-月 4-季 5-半年 6-年 7-指定日 8-半月 非周期结算
     */
    private String stlPerd;

    /**
     * 结算日 当结算周日为7时必填
     */
    private String stlDay;

    /**
     * 结算划款天数 只允许数字
     */
    private String stlTrfDays;

    /**
     * 下一结算日期 需大于当前日期
     */
    private String nextStlDt;

    /**
     * 暂停结算标志
     * 0-正常结算 1-暂停结算
     */
    private String pasStlFlg;

    /**
     * 结算起始金额
     * 以分为单位
     */
    private String stlBegAmt;

    /**
     * 最低留存金额
     * 以分为单位
     */
    private String minRtnAmt;

}
