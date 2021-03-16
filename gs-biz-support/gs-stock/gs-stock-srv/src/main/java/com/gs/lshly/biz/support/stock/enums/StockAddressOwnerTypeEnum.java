package com.gs.lshly.biz.support.stock.enums;


import com.gs.lshly.common.enums.EnumMessage;

public enum StockAddressOwnerTypeEnum implements EnumMessage {

    店铺(10, "店铺"),
    会员(20, "会员");

    StockAddressOwnerTypeEnum(Integer code, String remark){
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
