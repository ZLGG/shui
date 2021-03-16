package com.gs.lshly.biz.support.foundation.mapper.view;

import lombok.Data;

@Data
public class CorpTypeCertView {

    /**
     * 企业类型ID
     */
    private String id;

    /**
     * 企业类型名称
     */
    private String typeName;

    /**
     * 企业类型分组名称
     */
    private String typeGroup;

    /**
     * 证照ID
     */
    private String certId;
    /**
     * 证照名称
     */
    private String certName;

    /**
     * 是否必需上传
     */
    private Integer isNeed;

}
