package com.gs.lshly.biz.support.foundation.enums;


import com.gs.lshly.common.enums.EnumMessage;


public enum FeedbackHanderQueryEnum implements EnumMessage {

    待处理(0, "待处理"),
    已处理(1, "已处理"),
    全部状态(2, "全部状态");


    FeedbackHanderQueryEnum(Integer code, String remark){
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
