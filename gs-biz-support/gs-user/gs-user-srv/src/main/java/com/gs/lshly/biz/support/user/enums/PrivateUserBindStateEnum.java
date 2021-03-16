package com.gs.lshly.biz.support.user.enums;

import com.gs.lshly.common.enums.EnumMessage;

public enum PrivateUserBindStateEnum implements EnumMessage {

    关联(10, "关联"),
    解除(20, "解除");


    PrivateUserBindStateEnum(Integer code, String remark){
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
