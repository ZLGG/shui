package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 16:07 2020/10/16
 *
 */
public enum PayMethodTypeEnum implements EnumMessage{

    全部(0,"全部"),
    PC(10,"PC"),
    H5(20,"H5"),
    小程序(30,"小程序");

    private Integer code;
    private String remark;

    PayMethodTypeEnum(Integer code, String remark) {
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
