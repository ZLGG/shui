package com.gs.lshly.common.enums;


public enum SysAccessLogTypeEnum implements EnumMessage {

    平台操作日志(10, "平台操作日志"),
    平台登录日志(20, "平台登录日志"),
    会员登录日志(30, "会员登录日志"),
    商家登录日志(40, "商家登录日志");


    SysAccessLogTypeEnum(Integer code, String remark){
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
