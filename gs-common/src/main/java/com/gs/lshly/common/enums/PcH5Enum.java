package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 16:07 2020/10/16
 * 是否匿名咨询
 */
public enum PcH5Enum implements EnumMessage {
    YES(10,"是"),
    NO(20,"否"),
    全部(30,"全部");

    private Integer code;
    private String remark;

    PcH5Enum(Integer code, String remark) {
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
