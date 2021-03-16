package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 18:10 2020/11/6
 */
public enum GoodAuditRecordEnum implements EnumMessage{
    审核通过(10, "审核通过"),
    审核拒绝(20, "审核拒绝");


    GoodAuditRecordEnum(int code, String remark) {
        this.code =  code;
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
