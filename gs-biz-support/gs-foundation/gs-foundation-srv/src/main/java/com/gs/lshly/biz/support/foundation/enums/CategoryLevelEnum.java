package com.gs.lshly.biz.support.foundation.enums;

import com.gs.lshly.common.enums.EnumMessage;

public enum CategoryLevelEnum implements EnumMessage {

    一级(10, "一级"),
    二级(20, "二级");


    CategoryLevelEnum(Integer code, String remark){
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
