package com.gs.lshly.biz.support.trade.enums;


import com.gs.lshly.common.enums.EnumMessage;

public enum MarketPtCutStatusEnum implements EnumMessage {
//10=已取消 20=已结束 30=未审核 40=待开始 50=活动中 60=审核拒绝]
    已取消(10, "已取消"),
    已结束(20, "已结束"),
    未审核(30, "未审核"),
    待开始(40, "待开始"),
    活动中(50,"活动中"),
    审核拒绝(60,"审核拒绝") ;


    MarketPtCutStatusEnum(Integer code, String remark){
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
