package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 11:48 2020/10/21
 */
public enum ImageSizeTypeEnum implements EnumMessage {
    大图(10,"大图"),
    中图(20,"中图"),
    小图(30,"小图"),
    微图(40,"微图");

    private Integer code;
    private String remark;

    ImageSizeTypeEnum(Integer code, String remark) {
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
