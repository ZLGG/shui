package com.gs.lshly.biz.support.trade.enums;

import com.gs.lshly.common.enums.EnumMessage;

public enum PayTypeEnum implements EnumMessage {

    支付宝(10, "支付宝"),
    微信(20, "微信"),
    积分支付(30, "积分支付");


    PayTypeEnum(Integer code, String remark){
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
