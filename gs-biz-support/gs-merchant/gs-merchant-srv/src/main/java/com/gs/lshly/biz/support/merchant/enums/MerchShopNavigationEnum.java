package com.gs.lshly.biz.support.merchant.enums;


import com.gs.lshly.common.enums.EnumMessage;

public enum MerchShopNavigationEnum implements EnumMessage {
    一级分类(10, "一级分类"),
    二级分类(20, "二级分类");
    MerchShopNavigationEnum(Integer code, String remark){
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
