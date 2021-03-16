package com.gs.lshly.common.enums;

/**
 * @author lxus
 */
public enum MerchantLoginEnum implements EnumMessage {

    启用(10, "启用"),
    停用(20, "停用");


    MerchantLoginEnum(Integer code, String remark){
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
