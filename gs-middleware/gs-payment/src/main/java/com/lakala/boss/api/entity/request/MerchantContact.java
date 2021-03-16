package com.lakala.boss.api.entity.request;

import lombok.Data;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.request
 * @Description: 子商户联系人信息开始
 * @date Date : 2019年10月14日 14:02
 */
@Data
@ToString
public class MerchantContact {

    /**
     * 子商户联系人类型
     * 0 技术联系人
     * 1 业务联系人
     * 2 财务联系人
     * 3 紧急联系人
     * 4 发票联系人
     * 5 密钥联系人
     */
    private String contactType;

    /**
     * 子商户联系人姓名
     */
    private String contactName;

    /**
     * 子商户联系人手机
     */
    private String contactMobile;

    /**
     * 子商户联系人邮箱
     */
    private String contactEmail;

}
