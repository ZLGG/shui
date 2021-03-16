package com.gs.lshly.common.enums;

public enum SettlementEnum implements EnumMessage{
    未结算(10, "未结算"),
    已结算(20, "已结算"),
    普通结算(10, "普通结算"),
    售后结算(20, "售后结算");


    SettlementEnum(Integer code, String remark){
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
