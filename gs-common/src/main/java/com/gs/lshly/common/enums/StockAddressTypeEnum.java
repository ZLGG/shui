package com.gs.lshly.common.enums;

public enum StockAddressTypeEnum implements EnumMessage{

    收货(10,"收货"),
    发票(20,"发票"),
    发货(30,"发货"),
    退货(40,"退货");


    StockAddressTypeEnum(Integer code, String remark){
        this.code = code;
        this.remark = remark;
    }
    private Integer  code;

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
