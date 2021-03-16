package com.gs.lshly.biz.support.foundation.mapper.view;

import lombok.Data;

@Data
public class LegalCertView {

    /**
     * id
     */
    private String id;

    /**
     * 证照字典ID
     */
    private String certId;

    /**
     * 法人单位ID
     */
    private String legalId;

    /**
     * 证照文件路径
     */
    private String certUrl;


    /**
     * 证照名称
     */
    private String certName;



}
