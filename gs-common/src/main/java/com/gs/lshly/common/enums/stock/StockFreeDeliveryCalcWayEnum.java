package com.gs.lshly.common.enums.stock;

import com.gs.lshly.common.enums.EnumMessage;

/**
 * 包邮计算方式
 * @author lxus
 * @since 2020-11-18
 */
public enum StockFreeDeliveryCalcWayEnum implements EnumMessage{
    按数量(10,"按数量"),
    按金额(20,"按金额"),
    按数量金额组合(30,"按数量金额组合");

    private Integer code;
    private String remark;

    StockFreeDeliveryCalcWayEnum(Integer code, String remark) {
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
