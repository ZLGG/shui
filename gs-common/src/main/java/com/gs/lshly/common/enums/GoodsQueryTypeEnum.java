package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 13:29 2020/11/6
 */
public enum GoodsQueryTypeEnum implements EnumMessage{
    商品id(10,"商品id"),
    店铺id(20,"店铺id");

    private Integer code;
    private String remark;

    GoodsQueryTypeEnum(Integer code, String remark) {
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
