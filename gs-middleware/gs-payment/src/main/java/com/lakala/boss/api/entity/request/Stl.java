package com.lakala.boss.api.entity.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: gtli
 * Date: 2019-10-17
 * Time: 09:17
 * Description: No Description
 */
@Data
@ToString
public class Stl implements Serializable {
    /**
     * 子商户结算账号公私标志
     */

    private String stlSign;
    /**
     * 子商户自结算标志
     */
    private String selfStlFlg;
    /**
     * 银行代码
     */
    private String bankName;
    /**
     * 开户行名称
     */
    private String stlBankName;
    /**
     * 联行行号
     */
    @JSONField(name = "lBnkNo")
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
     * 商户结算类型1-结算到账户2-结算到银行账户
     */
    private String stlCls;
    /**
     * 结算周期
     * 0-日
     * 1-周
     * 2-旬
     * 3-月
     * 4-季
     * 5-半年
     * 6-年
     * 7-指定结算日
     */
    private String stlPerd;
    /**
     * 结算日
     */
    private String stlDay;
    /**
     * 结算划款天数
     */
    private String stlTrfDays;
    /**
     * 暂停结算标志
     */
    private String pasStlFlg;
    /**
     * 结算起始金额
     */
    private String stlBegAmt;
    /**
     * 最低留存金额
     */
    private String minRtnAmt;
    /**
     *下一结算日期
     */
    private String nextStlDt;

    /**
     *支行归属省
     */
    private String opnBnkProv;

    /**
     *支行归属市
     */
    private String opnBnkCity;


    public String getlBnkNo() {
        return lBnkNo;
    }

    public void setlBnkNo(String lBnkNo) {
        this.lBnkNo = lBnkNo;
    }
}
