package com.gs.lshly.common.enums;

/**
 * 交易订单来源类型
 * @author OY
 */
public enum TradeSourceTypeEnum implements EnumMessage {

    _2C(10, "_2C"),
    _2B(20, "_2B"),
    POS(30, "POS");


    TradeSourceTypeEnum(Integer code, String remark){
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
