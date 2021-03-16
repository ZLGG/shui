package com.gs.lshly.common.enums;

public enum PlatformCardStatusEnum implements EnumMessage{
    //促销状态枚举类型[10=待审核 20=审核通过 30=已取消 40=未审核 50=审核拒绝]
    待审核(10,"待审核"),
    审核通过(20,"审核通过"),
    已取消(30,"已取消"),
    未审核(40,"未审核"),
    审核拒绝(40,"审核拒绝");

    PlatformCardStatusEnum(Integer code, String remark){
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
