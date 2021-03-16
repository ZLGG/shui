package com.gs.lshly.common.enums;

/**
 * @author OY
 */
public enum TradeDeliveryTypeEnum implements EnumMessage {

    快递配送(10, "快递配送"),
    门店自提(20, "门店自提"),
    门店配送(30, "门店配送");


    TradeDeliveryTypeEnum(Integer code, String remark){
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
