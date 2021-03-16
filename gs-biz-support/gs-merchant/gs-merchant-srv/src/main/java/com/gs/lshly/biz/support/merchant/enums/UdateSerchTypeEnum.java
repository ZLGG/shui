package com.gs.lshly.biz.support.merchant.enums;


import com.gs.lshly.common.enums.EnumMessage;

public enum UdateSerchTypeEnum implements EnumMessage {

    今日(10, "今日"),
    昨日(20, "昨日"),
    最近7天(30, "最近七天"),
    最近30天(40, "最近30天");


    UdateSerchTypeEnum(Integer code, String remark){
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
