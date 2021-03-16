package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 20:09 2020/10/23
 */
public enum GoodsTypeEnum implements EnumMessage{
    标准商品(10,"标准商品"),
    扶贫商品(20,"扶贫商品");

    private Integer code;
    private String remark;

    GoodsTypeEnum(Integer code, String remark) {
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
