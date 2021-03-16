package com.gs.lshly.common.enums;

/**
 * @author OY
 */
public enum TradeCancelStateEnum implements EnumMessage {

    提交申请(10, "提交申请"),
    商家驳回(20, "商家驳回"),
    商家确认(30, "商家确认"),
    退款中(40, "退款中"),
    完成(50, "完成");


    TradeCancelStateEnum(Integer code, String remark){
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
