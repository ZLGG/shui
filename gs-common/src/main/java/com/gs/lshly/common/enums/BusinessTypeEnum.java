package com.gs.lshly.common.enums;


public enum BusinessTypeEnum implements EnumMessage {

    买家(10, "买家"),
    卖家(20, "卖家"),
    全部(30, "买家卖家");


    BusinessTypeEnum(Integer code, String remark){
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
