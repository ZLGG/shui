package com.gs.lshly.common.enums.merchant;


import com.gs.lshly.common.enums.EnumMessage;

public enum ShopStateEnum implements EnumMessage {

    关闭状态(10, "关闭状态"),
    开启状态(20, "开启状态");
    ShopStateEnum(Integer code, String remark){
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
