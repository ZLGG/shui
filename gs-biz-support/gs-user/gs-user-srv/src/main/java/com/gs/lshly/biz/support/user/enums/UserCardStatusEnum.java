package com.gs.lshly.biz.support.user.enums;


import com.gs.lshly.common.enums.EnumMessage;

public enum UserCardStatusEnum implements EnumMessage {
//10=未使用 20=已使用 30=已过期]
    未使用(10, "未使用"),
    已使用(20, "已使用");



    UserCardStatusEnum(Integer code, String remark){
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
