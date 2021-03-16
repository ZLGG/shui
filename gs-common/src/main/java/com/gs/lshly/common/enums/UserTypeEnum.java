package com.gs.lshly.common.enums;


public enum UserTypeEnum implements EnumMessage {

    _2B用户(10, "2B用户"),
    _2C用户(20, "2C用户"),
    _2B商家主账号(30, "2B商家账号"),
    _2B商家子账号(35, "2B商家子账号"),
    _2C商家主账号(40, "2C商家账号"),
    _2C商家子账号(40, "2C商家子账号"),
    平台运营账号(50, "平台运营账号"),
    平台超管账号(60, "平台超管账号");

    UserTypeEnum(Integer code, String remark){
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
