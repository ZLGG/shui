package com.gs.lshly.common.enums;

/**
 * @Author yangxi
 * @create 2021/4/13 10:24
 */
public enum QueryIntegralGoodsEnum implements EnumMessage{
    我能兑换(10,"我能兑换"),
    IN会员(20,"in会员"),
    销量(30,"销量"),
    价格(40,"价格"),
    上新(50,"上新")
    ;

    private Integer code;
    private String remark;

    QueryIntegralGoodsEnum(Integer code, String remark) {
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
