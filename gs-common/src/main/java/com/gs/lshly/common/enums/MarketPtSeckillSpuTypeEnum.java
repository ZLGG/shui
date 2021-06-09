package com.gs.lshly.common.enums;


public enum MarketPtSeckillSpuTypeEnum implements EnumMessage {

    普通商品(10, "普通商品"),
    积分商品(20, "积分商品");

    MarketPtSeckillSpuTypeEnum(Integer code, String remark) {
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
