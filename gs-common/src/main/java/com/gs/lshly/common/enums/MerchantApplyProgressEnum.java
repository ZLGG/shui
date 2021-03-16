package com.gs.lshly.common.enums;

/**
 * @author OY
 */
public enum MerchantApplyProgressEnum implements EnumMessage {

    企业信息(10, "企业信息"),
    银行信息(20, "银行信息"),
    店铺信息(30, "店铺信息"),
    证照信息(40, "证照信息"),
    等待审核(50, "等待审核"),
    审核失败(59, "审核失败"),
    等待签约(60, "等待签约"),
    进度信息(70, "进度信息");

    MerchantApplyProgressEnum(Integer code, String remark){
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
