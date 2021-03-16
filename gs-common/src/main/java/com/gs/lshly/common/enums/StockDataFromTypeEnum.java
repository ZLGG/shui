package com.gs.lshly.common.enums;

/**
 * @author lxus
 */
public enum StockDataFromTypeEnum implements EnumMessage {

    POS同步(10, "POS"),
    商家运维(20, "商家运维"),
    销售订单(30, "销售订单"),
    采购订单(40, "采购订单"),
    取消订单(50, "取消订单");


    StockDataFromTypeEnum(Integer code, String remark){
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
