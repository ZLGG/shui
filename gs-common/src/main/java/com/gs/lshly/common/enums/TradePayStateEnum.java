package com.gs.lshly.common.enums;

/**
 * @author OY
 */
public enum TradePayStateEnum implements EnumMessage {

    待支付(10, "待支付"),
    待确认(20, "待确认"),
    驳回(30, "驳回"),
    已支付(40, "已支付");

    TradePayStateEnum(Integer code, String remark){
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
