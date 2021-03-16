package com.gs.lshly.common.enums;

/**
 * @author OY
 */
public enum TradeCancelRefundStateEnum implements EnumMessage {

    无需退款(10, "无需退款"),
    等待审核(20, "等待审核"),
    等待退款(30, "等待退款"),
    退款成功(40, "退款成功");


    TradeCancelRefundStateEnum(Integer code, String remark){
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
