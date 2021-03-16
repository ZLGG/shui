package com.gs.lshly.common.enums;


public enum SiteAdvertEnum implements EnumMessage {

    单张广告图(10, "单张广告图"),
    通栏广告图(20, "通栏广告图");

    SiteAdvertEnum(Integer code, String remark){
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
