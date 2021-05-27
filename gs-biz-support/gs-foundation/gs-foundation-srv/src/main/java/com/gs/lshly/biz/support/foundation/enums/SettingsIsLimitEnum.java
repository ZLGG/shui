package com.gs.lshly.biz.support.foundation.enums;

import com.gs.lshly.common.enums.EnumMessage;

public enum SettingsIsLimitEnum implements EnumMessage {

    退货(10, "退货"),
    换货(20, "换货"),
    待付款(30, "待付款"),
    自动收货(40, "自动收货");


    SettingsIsLimitEnum(Integer code, String remark) {
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
