package com.lakala.boss.api.entity.request;

import lombok.Data;
import lombok.ToString;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.entity.request
 * @Description: 子商户法人信息
 * @date Date : 2019年10月14日 13:57
 */
@Data
@ToString
public class MerchantOwner {

    /**
     * 子商户法人姓名
     */
    private String enterpriseOwner;

    /**
     * 子商户法人证件类型
     */
    private String enterpriseOwnerIdType;

    /**
     * 子商户法人证件号码
     */
    private String enterpriseOwnerIdNo;

    /**
     * 子商户法人证件生效日期
     * YYYYMMDD
     */
    private String enterpriseOwnerIdEffDt;

    /**
     * 子商户法人证件失效日期
     * YYYYMMDD
     */
    private String enterpriseOwnerIdExpDt;

    /**
     * 子商户法人证件照片正面
     */
    private String ownImg1;

    /**
     * 子商户法人证件照片反面
     */
    private String othImg2;
}
