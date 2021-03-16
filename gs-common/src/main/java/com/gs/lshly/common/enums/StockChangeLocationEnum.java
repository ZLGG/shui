package com.gs.lshly.common.enums;

public enum StockChangeLocationEnum implements EnumMessage {

    增加(10, "增加"),
    减少(20, "减少");

    StockChangeLocationEnum(Integer code, String remark){
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
