package com.lakala.boss.api.entity.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: gtli
 * Date: 2019-10-17
 * Time: 09:26
 * Description: No Description
 */
@Data
@ToString
public class Enterprise implements Serializable {
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
     */
    private String enterpriseOwnerIdEffDt;
    /**
     * 子商户法人证件失效日期
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
