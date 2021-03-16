package com.lakala.boss.api.entity.request;

import lombok.Data;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.request
 * @Description: 子商户管理员信息开始
 * @date Date : 2019年10月14日 13:54
 */
@Data
@ToString
public class MerchantAdministrator {

    /**
     * 子商户管理员姓名
     */
    private String usrOprNm;

    /**
     * 子商户管理员手机号
     */
    private String usrOprMbl;

    /**
     * 子商户管理员Email
     */
    private String usrOprEmail;

    /**
     * 子商户接口类型 1-RSA
     */
    private String mercCertTyp;
}
