package com.gs.lshly.common.enums;

/**
 */
public enum ResponseCodeEnum implements EnumMessage{
    成功(200,"成功"),
    失败(500,"失败");

    private Integer code;
    private String remark;

    ResponseCodeEnum(Integer code, String remark) {
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
