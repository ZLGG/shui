package com.gs.lshly.biz.support.trade.enums;


import com.gs.lshly.common.enums.EnumMessage;

public enum MarketPtActivityCheckStatusEnum implements EnumMessage {
//10=未审核 20=审核通过 30=审核未通过 40=全部]
    未审核(10, "未审核"),
    审核通过(20, "审核通过"),
    审核未通过(30, "审核未通过"),
    全部(40, "全部");


    MarketPtActivityCheckStatusEnum(Integer code, String remark){
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
