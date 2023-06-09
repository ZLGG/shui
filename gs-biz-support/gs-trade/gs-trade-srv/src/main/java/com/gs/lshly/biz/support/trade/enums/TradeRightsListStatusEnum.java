package com.gs.lshly.biz.support.trade.enums;


import com.gs.lshly.common.enums.EnumMessage;

public enum TradeRightsListStatusEnum implements EnumMessage {
//状态枚举类[10=平台待审核 20=待确认收货 40=退货退款 50=换货]"
    换货(10, "换货"),
    仅退款(20, "仅退款"),
    退货退款(30, "退货退款");


    TradeRightsListStatusEnum(Integer code, String remark){
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
