package com.gs.lshly.biz.support.trade.enums;


import com.gs.lshly.common.enums.EnumMessage;

public enum MarketPtRightCheckStatusEnum implements EnumMessage {

    审核(10, "审核通过"),
    审核驳回(20, "审核驳回"),;


    MarketPtRightCheckStatusEnum(Integer code, String remark){
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
