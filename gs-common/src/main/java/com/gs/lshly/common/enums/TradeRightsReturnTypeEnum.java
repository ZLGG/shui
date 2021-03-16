package com.gs.lshly.common.enums;

/**
 * @author OY
 */
public enum TradeRightsReturnTypeEnum implements EnumMessage {

    自行寄回(10, "自行寄回"),
    上门取件(20, "上门取件");

    TradeRightsReturnTypeEnum(Integer code, String remark){
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
