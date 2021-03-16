package com.gs.lshly.biz.support.trade.enums;


import com.gs.lshly.common.enums.EnumMessage;

public enum TradeRefundStatusEnum implements EnumMessage {
    等待平台退款(10, "等待平台退款"),
    退款完成(20, "退款完成"),
    支付失败(30,"支付失败");

    TradeRefundStatusEnum(Integer code, String remark){
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
