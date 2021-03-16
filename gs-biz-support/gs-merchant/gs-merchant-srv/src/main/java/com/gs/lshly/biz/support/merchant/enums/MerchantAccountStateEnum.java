package com.gs.lshly.biz.support.merchant.enums;


import com.gs.lshly.common.enums.EnumMessage;

public enum MerchantAccountStateEnum implements EnumMessage {

    启用(10, "启用"),
    禁用(20, "禁用");


    MerchantAccountStateEnum(Integer code, String remark){
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
