package com.gs.lshly.common.enums;


public enum MarketPtSeckillSkuStateEnum implements EnumMessage {

    待审核(10, "待审核"),
    已通过(20, "已通过"),
    已驳回(20, "已驳回");

    MarketPtSeckillSkuStateEnum(Integer code, String remark) {
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
