package com.gs.lshly.biz.support.foundation.enums;

import com.gs.lshly.common.enums.EnumMessage;

public enum NoticeReadStateEnum implements EnumMessage {

    未读(10, "未读"),
    已读(20, "已读");


    NoticeReadStateEnum(Integer code, String remark){
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
