package com.gs.lshly.common.enums.merchant;


import com.gs.lshly.common.enums.EnumMessage;

/**
 * 店铺地区配置
 */
public enum ShopFixedMapEnum implements EnumMessage {

    关闭(10, "关闭"),
    启用(20, "启用");


    ShopFixedMapEnum(Integer code, String remark){
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
