package com.gs.lshly.common.enums;

public enum RemindReadEnum implements EnumMessage{
    全部(0 ,"全部"),
    待读(10,"待读"),
    已读(20,"已读");

    RemindReadEnum(Integer code, String remark){
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
