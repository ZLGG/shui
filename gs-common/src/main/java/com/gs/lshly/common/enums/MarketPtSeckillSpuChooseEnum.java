package com.gs.lshly.common.enums;


public enum MarketPtSeckillSpuChooseEnum implements EnumMessage {

    已选择(10, "已选择"),
    未选择(20, "未选择");

    MarketPtSeckillSpuChooseEnum(Integer code, String remark) {
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
