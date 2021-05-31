package com.gs.lshly.biz.support.trade.enums;

import com.gs.lshly.common.enums.EnumMessage;

public enum TradeRightsGoodsTypeEnum implements EnumMessage {

    原商品(10, "处理中"),
    换货商品(20, "已完成");


    TradeRightsGoodsTypeEnum(Integer code, String remark){
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
