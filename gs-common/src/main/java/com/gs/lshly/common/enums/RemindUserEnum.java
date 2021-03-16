package com.gs.lshly.common.enums;

public enum RemindUserEnum implements EnumMessage{


    /**
     * 订单发货提醒  = 10
     * 订单取消提醒  = 11
     * 物流状态通知（配送中、配送完成） = 12
     * 活动通知（平台） = 13
     */
    订单发货提醒(10,"订单发货提醒"),
    订单取消提醒(11,"订单取消提醒"),
    物流状态通知(12,"物流状态通知"),
    活动通知(13,"活动通知");

    RemindUserEnum(Integer code, String remark){
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
