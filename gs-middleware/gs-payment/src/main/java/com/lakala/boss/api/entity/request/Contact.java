package com.lakala.boss.api.entity.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: gtli
 * Date: 2019-10-22
 * Time: 16:59
 * Description: No Description
 */
@Data
@ToString
public class Contact implements Serializable {
    /**
     * 联系人类型 0 技术联系人 1 业务联系人 2 财务联系人 3 紧急联系人 4 发票联系人 5 密钥联系人	N
     */
    private String contactType;
    /**
     * 联系人姓名
     */
    private String contactName;
    /**
     * 联系人手机
     */
    private String contactMobile;
    /**
     * 联系人邮箱
     */
    private String contactEmail;

}
