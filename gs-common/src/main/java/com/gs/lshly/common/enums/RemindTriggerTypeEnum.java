package com.gs.lshly.common.enums;


public enum RemindTriggerTypeEnum implements EnumMessage {

    会员(10, "会员"),
    商家(20, "商家");


    RemindTriggerTypeEnum(Integer code, String remark){
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
