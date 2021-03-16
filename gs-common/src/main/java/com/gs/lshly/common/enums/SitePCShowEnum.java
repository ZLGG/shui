package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 14:05 2021/1/4
 */
public enum SitePCShowEnum implements EnumMessage {

    显示(10, "是"),
    不显示(20, "否");

    SitePCShowEnum(Integer code, String remark){
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
