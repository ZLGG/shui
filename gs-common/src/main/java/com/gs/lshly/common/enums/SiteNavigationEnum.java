package com.gs.lshly.common.enums;


public enum SiteNavigationEnum implements EnumMessage {

    顶部链接(10, "顶部链接"),
    菜单导航(20, "菜单导航");

    SiteNavigationEnum(Integer code, String remark){
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
