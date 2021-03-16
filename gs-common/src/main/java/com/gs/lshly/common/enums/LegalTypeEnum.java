package com.gs.lshly.common.enums;


public enum LegalTypeEnum implements EnumMessage {

    个人(10, "个人"),
    企业(20, "企业");


    LegalTypeEnum(Integer code, String remark){
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
