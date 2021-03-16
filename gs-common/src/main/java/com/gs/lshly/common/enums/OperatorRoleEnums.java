package com.gs.lshly.common.enums;

public enum OperatorRoleEnums implements EnumMessage {

    平台运营(10, "平台运营"),
    商家运营(20, "商家运营"),
    B端用户(30, "B端用户"),
    C端用户(40, "C端用户");

    OperatorRoleEnums(Integer code, String remark){
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