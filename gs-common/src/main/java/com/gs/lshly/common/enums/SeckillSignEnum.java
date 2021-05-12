package com.gs.lshly.common.enums;

public enum SeckillSignEnum implements EnumMessage{
    已审核(10,"已审核"),
    待审核(20,"待审核"),
    审核驳回(30,"审核驳回");

    SeckillSignEnum(Integer code, String remark){
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
