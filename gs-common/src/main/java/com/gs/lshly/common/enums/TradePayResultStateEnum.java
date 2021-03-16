package com.gs.lshly.common.enums;

/**
 * @author OY
 */
public enum TradePayResultStateEnum implements EnumMessage {

    SUCCESS(10, "SUCCESS"),
    FAILED(20, "FAILED");

    TradePayResultStateEnum(Integer code, String remark){
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
