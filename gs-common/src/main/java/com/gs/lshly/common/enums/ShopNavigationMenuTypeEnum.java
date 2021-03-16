package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 16:07 2020/10/16
 * 是否匿名咨询
 */
public enum ShopNavigationMenuTypeEnum implements EnumMessage{

    店铺分类(10,"店铺分类"),
    自定义图文(20,"自定义图文"),
    自定义文本(30,"自定义文本");
    private Integer code;
    private String remark;

    ShopNavigationMenuTypeEnum(Integer code, String remark) {
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
