package com.gs.lshly.common.enums;

/**
 * 业务终端: 10-2b;20-2c
 * @author Starry
 * @since  16:07 2020/10/16
 */
public enum TerminalEnum implements EnumMessage {
    BBB(10,"2B"),
    BBC(20,"2C");

    private Integer code;
    private String remark;

    TerminalEnum(Integer code, String remark) {
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
