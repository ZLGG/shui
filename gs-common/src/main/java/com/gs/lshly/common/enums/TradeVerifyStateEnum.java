package com.gs.lshly.common.enums;

/**
 * @author OY
 */
public enum TradeVerifyStateEnum implements EnumMessage {

    待确认(10, "待确认"),
    驳回(20, "驳回"),
    已确认(30, "已确认");

    TradeVerifyStateEnum(Integer code, String remark){
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
