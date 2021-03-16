package com.gs.lshly.common.enums;

public enum ActivitySignOperationEnum implements EnumMessage{

    审核通过(10,"审核通过"),
    审核(20,"审核"),
    审核驳回(30,"审核驳回"),
    无法审核(40,"无法审核");

    ActivitySignOperationEnum(Integer code, String remark){
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
