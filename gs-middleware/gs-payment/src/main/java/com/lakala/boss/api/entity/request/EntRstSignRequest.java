package com.lakala.boss.api.entity.request;

import com.lakala.boss.api.entity.response.EntRstSignResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.request
 * @Description: 商业委托短信下发接口
 * @date Date : 2019年10月14日 09:53
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class EntRstSignRequest extends BaseRequest<EntRstSignResponse> {

    /**
     * 业务类型
     */
    private String service = "EntRstSign";

    /**
     * 前台通知url
     * 网关签约交易结果通过前台通讯通知到这个url
     */
    private String pageNotifyUrl;

    /**
     * 后台通知url
     * 网关签约交易结果通过后台通讯通知到这个url
     */
    private String offlineNotifyUrl;

    /**
     * 客户号
     */
    private String purchaserId;

    /**
     * 持卡人姓名
     */
    private String capCrdName;

    /**
     * 证件类型
     */
    private String idType;

    /**
     * 持卡人证件号
     */
    private String idNo;

    /**
     * 卡号
     */
    private String crdNo;

    /**
     * 卡有效期
     */
    private String crdExpDat;

    /**
     * 卡校验码
     */
    private String cvn2;

    /**
     * 卡预留手机号
     */
    private String bnkPhone;

    /**
     * 委托起始时间
     * 时间格式：yyyyMMddHHmmss
     */
    private String entRstStrTm;

    /**
     * 委托结束时间
     * 时间格式：yyyyMMddHHmmss
     */
    private String entRstEndTm;

    /**
     * 委托限额
     */
    private String entRstAmtLmt;

    /**
     * 委托扣款时间单位
     * 00：年 04：月 07：日
     */
    private String entRstDtUnt;

    /**
     * 扣款步长
     * 委托协议扣款时间单位”为“月”，步长为“3”，即3个月扣款成功一次
     */
    private String entRstDtStp;

    /**
     * 委托款项用途
     */
    private String entRstDtDsc;

    @Override
    public Class<EntRstSignResponse> getResponseClass() {
        return EntRstSignResponse.class;
    }
}
