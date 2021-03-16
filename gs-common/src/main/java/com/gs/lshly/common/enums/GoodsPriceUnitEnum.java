package com.gs.lshly.common.enums;

/**
 * @author lxus
 * @since 2020/11/17
 */
public enum GoodsPriceUnitEnum implements EnumMessage {

    件(10, "件"),
    个(20, "个"),
    箱(30, "箱"),
    套(30, "箱"),
    千克(40, "千克");


    GoodsPriceUnitEnum(Integer code, String remark){
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
