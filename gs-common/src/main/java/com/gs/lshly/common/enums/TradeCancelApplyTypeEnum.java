package com.gs.lshly.common.enums;

/**
 * @author OY
 */
public enum TradeCancelApplyTypeEnum implements EnumMessage {

    用户取消订单(10, "用户取消订单"),
    商家取消订单(20, "商家取消订单");


    TradeCancelApplyTypeEnum(Integer code, String remark){
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
