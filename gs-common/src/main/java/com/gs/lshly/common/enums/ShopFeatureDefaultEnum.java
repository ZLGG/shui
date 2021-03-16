package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 16:07 2020/10/16
 *
 */
public enum ShopFeatureDefaultEnum implements EnumMessage{

    满分(5,"满分"),
    中等(3,"中等"),
    低等(2,"低等");

    private Integer code;
    private String remark;

    ShopFeatureDefaultEnum(Integer code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getRemark() {
        return remark;
    }
}
