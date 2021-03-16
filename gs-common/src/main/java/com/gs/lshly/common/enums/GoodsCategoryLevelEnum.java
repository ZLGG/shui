package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 15:34 2020/10/23
 */
public enum GoodsCategoryLevelEnum implements EnumMessage{
    ONE(1,"一级类目"),
    TWO(2,"二级类目"),
    THREE(3,"三级类目");

    private Integer code;
    private String remark;

    GoodsCategoryLevelEnum(Integer code, String remark) {
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
