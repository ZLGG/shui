package com.gs.lshly.common.enums;

/**
 * 商家运费模板计价方式
 * @author zhou
 * @date 17:55 2020/10/10
 */
public enum FreightChargeCalculateWayEnum implements EnumMessage {
    AMOUNT_WAY(10,"金额方式计价"),
    NUMBER_WAY(20,"数量方式计价"),
    WEIGHT_WAY(30,"重量方式计价")
    ;

    FreightChargeCalculateWayEnum(int code, String remark){
        this.code = code;
        this.remark = remark;
    }

     int code;
     String remark;

    @Override
    public Integer getCode() {
        return code;
    }
    @Override
    public String getRemark() {
        return remark;
    }
}
