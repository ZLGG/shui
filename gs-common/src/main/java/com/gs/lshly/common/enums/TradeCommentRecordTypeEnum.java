package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 15:06 2020/11/17
 */
public enum TradeCommentRecordTypeEnum implements EnumMessage{
    平台(10,"平台"),
    店铺(20,"店铺");

    private Integer code;
    private String remark;

    TradeCommentRecordTypeEnum(Integer code, String remark) {
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
