package com.gs.lshly.common.enums;

public enum StockKdniaoEnum implements EnumMessage{
    开启(10,"开启"),
    关闭(20,"关闭");

    StockKdniaoEnum(Integer code, String remark){
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
