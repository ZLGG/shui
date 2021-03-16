package com.gs.lshly.biz.support.stock.enums;


import com.gs.lshly.common.enums.EnumMessage;

public enum StockTemplateRegionTpEnum implements EnumMessage {

    计重计件(10, "计重计件"),
    金额范围(20, "金额范围"),
    包邮条件(30, "包邮条件");
    StockTemplateRegionTpEnum(Integer code, String remark){
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
