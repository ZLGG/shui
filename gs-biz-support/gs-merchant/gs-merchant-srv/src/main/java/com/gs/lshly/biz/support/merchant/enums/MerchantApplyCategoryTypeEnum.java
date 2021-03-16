package com.gs.lshly.biz.support.merchant.enums;


import com.gs.lshly.common.enums.EnumMessage;

public enum MerchantApplyCategoryTypeEnum implements EnumMessage {

    入驻申请(10, "入驻申请"),
    店铺申请(20, "店铺申请");
    MerchantApplyCategoryTypeEnum(Integer code, String remark){
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
