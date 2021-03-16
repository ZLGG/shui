package com.gs.lshly.common.enums;

public enum TradeInvoiceAddressEnum implements EnumMessage{
    默认地址(10, "默认地址"),
    普通地址(20, "普通地址");


    TradeInvoiceAddressEnum(Integer code, String remark){
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
