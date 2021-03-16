package com.gs.lshly.biz.support.foundation.enums;


import com.gs.lshly.common.enums.EnumMessage;
import io.swagger.annotations.ApiModelProperty;


public enum FeedbackQueryTypeEnum implements EnumMessage {

    用户名(10, "用户名"),
    邮箱(20, "邮箱"),
    电话(30, "电话"),
    处理状态(40, "处理状态");

    FeedbackQueryTypeEnum(Integer code, String remark){
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
