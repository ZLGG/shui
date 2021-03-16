package com.gs.lshly.common.enums;

public enum StatementEnum implements EnumMessage{
    昨日(10, "昨日"),
    前日(20, "前日"),
    一周(30, "一周"),
    一月(40, "一月"),
    销售金额(50, "销售金额"),
    订单数量(60, "订单数量"),
    商品数量(70, "商品数量"),
    平均订单价(80, "平均订单价");

    StatementEnum(Integer code, String remark){
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
