package com.gs.lshly.biz.support.foundation.enums;


import com.gs.lshly.common.enums.EnumMessage;

public enum LegalDictCorpQueryTypeEnum implements EnumMessage {

    公司名称(10, "公司名称"),
    法人代表(20, "法人代表"),
    公司联系人手机号(30, "公司名称");


    LegalDictCorpQueryTypeEnum(Integer code, String remark){
        this.code = code;
        this.remark = remark;
    }

    private Integer code;

    private String remark;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getRemark() {
        return remark;
    }
}
