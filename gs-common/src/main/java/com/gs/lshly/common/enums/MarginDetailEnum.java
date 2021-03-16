package com.gs.lshly.common.enums;

public enum MarginDetailEnum implements EnumMessage{
    充值(10, "充值"),
    扣款(20, "扣款"),
    额度调整(30, "额度调整");

    MarginDetailEnum(Integer code, String remark){
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
