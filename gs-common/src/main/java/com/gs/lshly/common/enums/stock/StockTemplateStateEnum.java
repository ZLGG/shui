package com.gs.lshly.common.enums.stock;

import com.gs.lshly.common.enums.EnumMessage;

/**
 * @author lxus
 */
public enum StockTemplateStateEnum implements EnumMessage {

    启用(10, "启用"),
    停用(20, "停用");


    StockTemplateStateEnum(Integer code, String remark){
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
