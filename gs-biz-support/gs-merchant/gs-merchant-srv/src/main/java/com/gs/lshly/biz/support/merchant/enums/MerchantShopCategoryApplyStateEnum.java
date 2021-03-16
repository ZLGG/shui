package com.gs.lshly.biz.support.merchant.enums;


import com.gs.lshly.common.enums.EnumMessage;

public enum MerchantShopCategoryApplyStateEnum implements EnumMessage {

    待审(10, "待审"),
    通过(20, "通过"),
    拒审(30, "拒审");
    MerchantShopCategoryApplyStateEnum(Integer code, String remark){
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
