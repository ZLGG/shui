package com.gs.lshly.common.enums;

public enum TradeMarginEnum implements EnumMessage{
    正常(10, "正常"),
    预警(20, "预警"),
    欠费(30, "欠费");


    TradeMarginEnum(Integer code, String remark){
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
