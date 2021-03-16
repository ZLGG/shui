package com.gs.lshly.biz.support.merchant.enums;


import com.gs.lshly.common.enums.EnumMessage;

public enum ShopSearchStyleEnum implements EnumMessage {
    //TODO 确认店铺搜索方式有哪些
    店铺名称(10, "店铺名称"),
    手机号(20, "手机号");
    ShopSearchStyleEnum(Integer code, String remark){
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
