package com.gs.lshly.common.enums;

public enum RemindMerchantEnum implements EnumMessage{

    待发货提醒(10,"待发货提醒"),
    用户催单提醒(20,"用户催单提醒"),
    取消订单待审核(30,"取消订单待审核"),
    活动报名提醒(40,"活动报名提醒"),
    退换货待审核(50,"退换货待审核"),
    咨询待回复提醒(60,"咨询待回复提醒"),
    平台通知(70,"平台通知");

    RemindMerchantEnum(Integer code, String remark){
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
