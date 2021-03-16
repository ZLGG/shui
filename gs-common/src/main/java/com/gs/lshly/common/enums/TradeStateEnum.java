package com.gs.lshly.common.enums;

/**
 * @author OY
 */
public enum TradeStateEnum implements EnumMessage {

    待支付(10, "待支付"),
    待发货(20, "待发货"),
    待收货(30, "待收货"),
    已完成(40, "已完成"),
    已取消(50, "已取消");


    TradeStateEnum(Integer code, String remark){
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
