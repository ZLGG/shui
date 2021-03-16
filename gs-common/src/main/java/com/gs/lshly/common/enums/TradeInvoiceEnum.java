package com.gs.lshly.common.enums;

public enum TradeInvoiceEnum implements EnumMessage{
    待开具(10, "待开"),
    已开具(20, "已开具"),
    已邮寄(30, "已邮寄"),
    已完成(40, "已完成"),

    个人(10, "个人"),
    企业(20, "企业"),

    默认发票(10, "默认发票"),
    普通发票(20, "普通发票"),

    增值税普通发票(10, "增值税普通发票"),
    增值税专用发票(20, "增值税专用发票");


    TradeInvoiceEnum(Integer code, String remark){
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
