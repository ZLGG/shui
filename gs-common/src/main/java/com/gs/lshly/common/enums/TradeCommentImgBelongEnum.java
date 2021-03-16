package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 10:43 2020/11/18
 */
public enum TradeCommentImgBelongEnum implements EnumMessage{
    初评(10,"初评"),
    追评(20,"追评"),
    初次申诉(30,"初次申诉"),
    再次申诉(40,"再次申诉");

    private Integer code;
    private String remark;

    TradeCommentImgBelongEnum(Integer code, String remark) {
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
