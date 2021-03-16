package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 16:07 2020/10/16
 * 是否匿名咨询
 */
public enum AnonymousEnum  implements EnumMessage{
    实名(10,"实名"),
    匿名(20,"匿名");

    private Integer code;
    private String remark;

    AnonymousEnum(Integer code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getRemark() {
        return remark;
    }
}
