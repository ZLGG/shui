package com.gs.lshly.common.enums;

/**
 * @author OY
 */
public enum TradeRightsReasonTypeEnum implements EnumMessage {

    质量问题(10, "质量问题"),
    七天无理由(20, "七天无理由"),
    地址信息填写错误(30, "地址信息填写错误"),
    不想要了(40, "不想要了"),
    价格有点贵(50, "价格有点贵"),
    商品错选(60, "商品错选"),
    商品无货(70, "商品无货"),
    其他(80, "其他"),
    取消订单(90,"取消订单");

    TradeRightsReasonTypeEnum(Integer code, String remark){
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
