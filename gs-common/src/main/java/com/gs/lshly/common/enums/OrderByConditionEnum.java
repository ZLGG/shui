package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 16:45 2020/10/24
 */
public enum OrderByConditionEnum implements  EnumMessage{
    销售(10,"销量"),
    评价(20,"评价"),
    价格(30,"价格"),
    兑换积分(40,"兑换积分"),
    上架时间(50,"上架时间");

    private Integer code;
    private String remark;

    OrderByConditionEnum(Integer code, String remark) {
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
