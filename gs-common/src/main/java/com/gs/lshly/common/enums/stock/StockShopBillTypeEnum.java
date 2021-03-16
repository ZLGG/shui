package com.gs.lshly.common.enums.stock;

import com.gs.lshly.common.enums.EnumMessage;

/**
 * 门店配送运费计算方式
 * @authorlxus
 * @since  2020-11-18
 * 是否匿名咨询
 */
public enum StockShopBillTypeEnum implements EnumMessage{
    自定义费用(10,"自定义费用"),
    按重量(20,"按重量"),
    按件数(30,"按件数");

    private Integer code;
    private String remark;

    StockShopBillTypeEnum(Integer code, String remark) {
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
