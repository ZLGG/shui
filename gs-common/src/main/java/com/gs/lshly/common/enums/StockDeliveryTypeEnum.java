package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 16:07 2020/10/16
 * 是否匿名咨询
 */
public enum StockDeliveryTypeEnum implements EnumMessage{
    快递配送(10,"快递配送"),
    门店自提(20,"门店自提"),
    门店配送(30,"门店配送");

    private Integer code;
    private String remark;

    StockDeliveryTypeEnum(Integer code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getRemark() {
        return remark;
    }
}
