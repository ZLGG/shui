package com.gs.lshly.common.enums;

import com.gs.lshly.common.enums.EnumMessage;

public enum SiteTopicCategoryEnum implements EnumMessage {

    电信甄选(10, "电信甄选"),
    名品集市(20, "名品集市"),
    ;

    SiteTopicCategoryEnum(Integer code, String remark){
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
