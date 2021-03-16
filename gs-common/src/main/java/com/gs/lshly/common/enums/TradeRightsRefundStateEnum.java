package com.gs.lshly.common.enums;

/**
 * @author OY
 */
public enum TradeRightsRefundStateEnum implements EnumMessage {

    成功(10, "成功"),
    失败(20, "失败");

    TradeRightsRefundStateEnum(Integer code, String remark){
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
