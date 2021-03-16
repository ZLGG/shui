package com.gs.lshly.common.enums;


public enum ApplyStateEnum implements EnumMessage {

    待审(10, "待审"),
    通过(20, "通过"),
    拒审(30, "拒审");


    ApplyStateEnum(Integer code, String remark){
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
