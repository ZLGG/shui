package com.gs.lshly.common.enums;

public enum PlataRefundTypeEnum implements EnumMessage{
    线下退款(10,"线下退款"),
    原路返回(20,"原路返回");

    PlataRefundTypeEnum(Integer code, String remark){
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
