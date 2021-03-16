package com.gs.lshly.biz.support.foundation.enums;


import com.gs.lshly.common.enums.EnumMessage;

public enum CorpTypeDictQueryTypeEnum implements EnumMessage {

    企业类型名称(10, "企业类型名称");


    CorpTypeDictQueryTypeEnum(Integer code, String remark){
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
