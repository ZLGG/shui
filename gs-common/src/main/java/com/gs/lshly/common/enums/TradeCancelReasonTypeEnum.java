package com.gs.lshly.common.enums;

/**
 * @author OY
 */
public enum TradeCancelReasonTypeEnum implements EnumMessage {

    地址信息填写错误(10, "地址信息填写错误"),
    不想要了(20, "不想要了"),
    价格有点贵(30, "价格有点贵"),
    商品错选或多选(40, "商品错选或多选"),
    商品无货(50, "商品无货"),
    其他(60, "其他");


    TradeCancelReasonTypeEnum(Integer code, String remark){
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
