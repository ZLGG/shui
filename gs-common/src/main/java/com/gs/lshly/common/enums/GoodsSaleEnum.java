package com.gs.lshly.common.enums;

import com.gs.lshly.common.enums.EnumMessage;

/**
 * @Author zst
 * @Date 13:29 2021/1/4
 */
public enum GoodsSaleEnum implements EnumMessage{
    销售数量(10,"销售数量"),
    销售金额(20,"销售金额");

    private Integer code;
    private String remark;

    GoodsSaleEnum(Integer code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    @Override
    public Integer    getCode() {
        return code;
    }

    @Override
    public String getRemark() {
        return remark;
    }
}
