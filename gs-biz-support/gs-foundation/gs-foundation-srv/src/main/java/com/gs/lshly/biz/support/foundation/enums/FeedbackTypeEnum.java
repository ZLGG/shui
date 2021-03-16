package com.gs.lshly.biz.support.foundation.enums;


import com.gs.lshly.common.enums.EnumMessage;

public enum FeedbackTypeEnum implements EnumMessage {

    商家(10, "商家"),
    会员(20, "会员");


    FeedbackTypeEnum(Integer code, String remark){
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
