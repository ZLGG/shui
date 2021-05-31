package com.gs.lshly.common.enums;

/**
 * @author OY
 */
public enum TradeRightsRefundMoneyTypeEnum implements EnumMessage {

    取消订单退款(10, "取消订单退款"),
    售后申请退款(20, "售后申请退款");


    TradeRightsRefundMoneyTypeEnum(Integer code, String remark){
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
