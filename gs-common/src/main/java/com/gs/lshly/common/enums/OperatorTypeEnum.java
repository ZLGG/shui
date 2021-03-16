package com.gs.lshly.common.enums;

/**
 * @author lxus
 */
public enum OperatorTypeEnum implements EnumMessage {

    商家(10, "商家"),
    会员(20, "会员");


    OperatorTypeEnum(Integer code, String remark){
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
