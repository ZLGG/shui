package com.gs.lshly.biz.support.foundation.enums;


import com.gs.lshly.common.enums.EnumMessage;

public enum LegalDictNalQueryTypeEnum implements EnumMessage {

    姓名(10, "姓名"),
    手机号(20, "手机号");


    LegalDictNalQueryTypeEnum(Integer code, String remark){
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
