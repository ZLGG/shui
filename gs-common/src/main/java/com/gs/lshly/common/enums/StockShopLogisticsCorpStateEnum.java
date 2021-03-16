package com.gs.lshly.common.enums;

public enum StockShopLogisticsCorpStateEnum implements EnumMessage{

    启用(10,  "启用"),
    停用(20,  "禁用");


    StockShopLogisticsCorpStateEnum(Integer code, String remark){
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
