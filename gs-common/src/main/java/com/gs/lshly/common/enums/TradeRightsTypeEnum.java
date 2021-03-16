package com.gs.lshly.common.enums;

/**
 * @author OY
 */
public enum TradeRightsTypeEnum implements EnumMessage {

    换货(10, "换货"),
    仅退款(20, "仅退款"),
    退货退款(30, "退货退款"),
    取消订单(40,"取消订单");

    TradeRightsTypeEnum(Integer code, String remark){
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
