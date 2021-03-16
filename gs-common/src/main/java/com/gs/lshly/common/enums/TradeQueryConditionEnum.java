package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 16:57 2020/11/17
 */
public enum TradeQueryConditionEnum implements EnumMessage{
    全部(10,"全部"),
    待处理(20,"待处理"),
    已处理(30,"已处理");

    private Integer code;
    private String remark;

    TradeQueryConditionEnum(Integer code, String remark) {
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
