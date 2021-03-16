package com.gs.lshly.common.enums;

public enum  activityRemindersEnum implements EnumMessage{
    短信(10,"短信"),
    邮件(20,"邮件");

    activityRemindersEnum (Integer code, String remark){
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
