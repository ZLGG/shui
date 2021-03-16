package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 16:07 2020/10/16
 */
public enum UserMerchantTtypeEnum implements EnumMessage{
    会员(10,"会员"),
    商家(20,"商家");

    private Integer code;
    private String remark;

    UserMerchantTtypeEnum(Integer code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getRemark() {
        return remark;
    }
}
