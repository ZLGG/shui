package com.gs.lshly.biz.support.user.enums;


import com.gs.lshly.common.enums.EnumMessage;

public enum UserQueryTypeEnum implements EnumMessage {
//@ApiModelProperty("查询类型[10=用户名 20=手机号 30=真实姓名 40=标签]")
    用户名(10, "用户名"),
    手机号(20, "手机号"),
    真实姓名(30, "真实姓名"),
    标签(40, "标签"),
    邮箱(50, "邮箱");


    UserQueryTypeEnum(Integer code, String remark){
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
