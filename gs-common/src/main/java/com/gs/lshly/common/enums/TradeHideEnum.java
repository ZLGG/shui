package com.gs.lshly.common.enums;

/**
 * @author OY
 */
public enum TradeHideEnum implements EnumMessage {

    隐藏(1, "隐藏"),
    显示(0, "显示");


    TradeHideEnum(Integer code, String remark){
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
