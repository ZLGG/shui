package com.gs.lshly.common.enums;

/**
 * @author lxus
 */
public enum StockLocationEnum implements EnumMessage {

    增加(10, "增加"),
    减少(20, "减少"),
    初始化(30, "初始化");


    StockLocationEnum(Integer code, String remark){
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
