package com.gs.lshly.biz.support.user.enums;


import com.gs.lshly.common.enums.EnumMessage;

public enum IntegralFromTypeEnum implements EnumMessage {

    平台添加(10, "平台添加"),
    平台扣除(20, "平台扣除"),
    订单(30, "订单");

    IntegralFromTypeEnum(Integer code, String remark){
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
