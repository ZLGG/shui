package com.gs.lshly.common.enums;


public enum RemindStyleTypeEnum implements EnumMessage {

    站内信(10, "站内信"),
    微信消息(20, "微信消息");

    RemindStyleTypeEnum(Integer code, String remark){
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
