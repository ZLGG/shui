package com.gs.lshly.common.enums;

/**
 * @Author yangxi
 * @create 2021/4/15 14:54
 */
public enum MallCategoryEnum implements EnumMessage {

    电信商城(0, "电信商城"),
    积分商城(1, "积分商城"),
    我能兑换(2,"我能兑换");


    MallCategoryEnum(Integer code, String remark){
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
