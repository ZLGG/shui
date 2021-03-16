package com.gs.lshly.common.enums;

public enum TradeDetailStateEnum implements EnumMessage{
    //订单详情状态[1=订单已生成等待您付款 2=您已付款成功等待商家发货 3=商家已发货等待您确认收货 4=订单完成 5=订单取消]
    订单已生成等待您付款(1,"订单已生成等待您付款"),
    您已付款成功等待商家发货(2,"您已付款成功等待商家发货"),
    商家已发货等待您确认收货(3,"商家已发货等待您确认收货"),
    订单完成(4,"订单完成"),
    订单取消(5,"订单取消");

    TradeDetailStateEnum(Integer code, String remark){
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
