package com.gs.lshly.common.enums.user;

import com.gs.lshly.common.enums.EnumMessage;

public enum TimePropEnum implements EnumMessage {
    早于(10,"早于"),
    晚于(20,"晚于"),
    是(30,"是"),
    介于(40,"介于");

    TimePropEnum(Integer code, String remark){
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
