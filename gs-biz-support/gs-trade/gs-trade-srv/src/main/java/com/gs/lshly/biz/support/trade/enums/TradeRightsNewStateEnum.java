package com.gs.lshly.biz.support.trade.enums;

import com.gs.lshly.common.enums.EnumMessage;

public enum TradeRightsNewStateEnum implements EnumMessage {

    处理中(10, "处理中"),
    已完成(20, "已完成"),
    待审核(30, "待审核");


    TradeRightsNewStateEnum(Integer code, String remark){
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
