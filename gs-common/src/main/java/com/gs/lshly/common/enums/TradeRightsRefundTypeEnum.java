package com.gs.lshly.common.enums;

/**
 * @author OY
 */
public enum TradeRightsRefundTypeEnum implements EnumMessage {

    原路退回(10, "原路退回");


    TradeRightsRefundTypeEnum(Integer code, String remark){
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
