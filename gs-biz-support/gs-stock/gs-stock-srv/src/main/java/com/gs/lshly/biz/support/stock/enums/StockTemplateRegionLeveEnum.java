package com.gs.lshly.biz.support.stock.enums;


import com.gs.lshly.common.enums.EnumMessage;

public enum StockTemplateRegionLeveEnum implements EnumMessage {

    省(10, "省"),
    市(20, "市");
    StockTemplateRegionLeveEnum(Integer code, String remark){
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
