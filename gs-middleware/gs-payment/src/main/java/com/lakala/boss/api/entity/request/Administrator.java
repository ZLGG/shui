package com.lakala.boss.api.entity.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: gtli
 * Date: 2019-10-16
 * Time: 17:03
 * Description: No Description
 */
@Data
@ToString
public class Administrator implements Serializable {

    /**
     *子商户管理员姓名
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
     * 子商户接口类型
     */
    private String mercCertTyp;
}
