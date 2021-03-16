package com.gs.lshly.common.enums;

/**
 * @author zst
 */
public enum TradePayOfficeEnum implements EnumMessage {

    确认(10, "确认"),
    驳回(20, "驳回"),
    待确认(30, "待确认"),
    审核通过(40, "审核通过"),
    审核拒绝(50, "审核拒绝");

    TradePayOfficeEnum(Integer code, String remark){
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
