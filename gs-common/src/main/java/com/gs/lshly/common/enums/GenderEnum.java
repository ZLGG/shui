package com.gs.lshly.common.enums;


public enum GenderEnum implements EnumMessage {

    男(10, "男"),
    女(20, "女"),
    未知(30, "未知");


    GenderEnum(Integer code, String remark){
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
