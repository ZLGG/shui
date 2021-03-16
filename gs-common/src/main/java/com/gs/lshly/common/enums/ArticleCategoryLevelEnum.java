package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 16:07 2020/10/16
 * 是否匿名咨询
 */
public enum ArticleCategoryLevelEnum implements EnumMessage {
    ONE(10,"一级"),
    TWO(20,"二级");

    private Integer code;
    private String remark;

    ArticleCategoryLevelEnum(Integer code, String remark) {
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
