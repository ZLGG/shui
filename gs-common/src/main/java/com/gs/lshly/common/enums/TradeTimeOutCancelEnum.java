package com.gs.lshly.common.enums;

/**
 * 是否超时取消订单
 * @Author OY
 */
public enum TradeTimeOutCancelEnum implements EnumMessage{
    超时取消(10, "是"),
    买家取消(20, "否");


    TradeTimeOutCancelEnum(Integer code, String remark){
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
