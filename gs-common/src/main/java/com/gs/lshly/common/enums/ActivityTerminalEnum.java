package com.gs.lshly.common.enums;

/**
 * 营销活动适用(10=全平台 20=pc端 30=wap端 40=app端)
 */
public enum ActivityTerminalEnum implements EnumMessage{
    全平台(10,"全平台"),
    pc端(20,"pc端"),
    wap端(30,"wap端"),
    app端(40,"app端");

    ActivityTerminalEnum(Integer code, String remark){
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
