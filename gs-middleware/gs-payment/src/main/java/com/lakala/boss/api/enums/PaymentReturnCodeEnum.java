package com.lakala.boss.api.enums;

/**
 * @author OY
 */
public enum PaymentReturnCodeEnum {

    支付处理中("RPM50014", "支付处理中"),
    订单已失效("RPM50002", "订单已失效");

    PaymentReturnCodeEnum(String code, String remark){
        this.code = code;
        this.remark = remark;
    }

    private String code;

    private String remark;

    public String getCode() {
        return code;
    }

    public String getRemark() {
        return remark;
    }
}
