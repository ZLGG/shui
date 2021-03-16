package com.gs.lshly.common.enums;

public enum MarginEnum implements EnumMessage{
    大于(10, "大于"),
    小于(20, "小于"),
    等于(30, "等于"),
    大于等于(40, "大于等于"),
    小于等于(50, "小于等于"),
    介于(60, "介于");

    MarginEnum(Integer code, String remark){
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
