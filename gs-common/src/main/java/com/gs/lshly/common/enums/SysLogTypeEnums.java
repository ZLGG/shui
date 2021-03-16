package com.gs.lshly.common.enums;

public enum SysLogTypeEnums implements EnumMessage {

    后台登陆(10, "后台登陆"),
    后台操作(15, "后台操作"),
    商家登陆(20, "商家登陆"),
    商家操作(25, "商家操作"),
    B端用户登陆(30, "B端用户登陆"),
    B端用户操作(35, "B端用户操作"),
    C端用户登陆(40, "C端用户登陆"),
    C端用户操作(45, "C端用户操作"),
    POS同步(55, "POS同步");

    SysLogTypeEnums(Integer code, String remark){
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