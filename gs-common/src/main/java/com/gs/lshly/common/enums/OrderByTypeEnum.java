package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 16:45 2020/10/24
 */
public enum OrderByTypeEnum implements EnumMessage{

    升序(10,"升序"),
    降序(20,"降序");

    private Integer code;
    private String remark;

    OrderByTypeEnum(Integer code, String remark) {
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
