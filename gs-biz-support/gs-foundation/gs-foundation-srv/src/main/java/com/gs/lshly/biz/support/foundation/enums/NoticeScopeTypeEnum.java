package com.gs.lshly.biz.support.foundation.enums;

import com.gs.lshly.common.enums.EnumMessage;

public enum NoticeScopeTypeEnum implements EnumMessage {

    全部商家(10, "全部商家"),
    ID商家(20, "ID商家");


    NoticeScopeTypeEnum(Integer code, String remark){
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
