package com.gs.lshly.common.enums;

/**
 * @author OY
 */
public enum TradeAppealStateEnum implements EnumMessage {

    未申诉(10, "未申诉"),
    申诉(20, "申诉"),
    驳回(30, "驳回"),
    通过(40, "通过"),
    关闭(50,"申诉关闭");


    TradeAppealStateEnum(Integer code, String remark){
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
