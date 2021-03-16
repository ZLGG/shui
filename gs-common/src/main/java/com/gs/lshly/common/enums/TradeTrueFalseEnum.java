package com.gs.lshly.common.enums;

/**
 * 是否
 * @Author OY
 */
public enum TradeTrueFalseEnum implements EnumMessage{
    是(10, "是"),
    否(20, "否");


    TradeTrueFalseEnum(Integer code, String remark){
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
