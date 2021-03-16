package com.gs.lshly.common.enums;


public enum OAuth2UserTypeEnum implements EnumMessage {

    平台账号(10, "平台账号"),
    商家账号(20, "商家账号");

    OAuth2UserTypeEnum(Integer code, String remark){
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
