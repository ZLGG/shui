package com.gs.lshly.common.enums;


public enum SysUserTypeEnum implements EnumMessage {

    运营管理账号(10, "运营管理账号"),
    超级管理账号(20, "超级管理账号");

    SysUserTypeEnum(Integer code, String remark){
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
