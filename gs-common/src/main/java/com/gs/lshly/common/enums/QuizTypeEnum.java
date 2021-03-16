package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 14:36 2020/10/16
 */
public enum QuizTypeEnum implements EnumMessage{
    商品咨询(10,"商品咨询"),
    库存配送(20,"库存配送"),
    支付方式(30,"支付方式"),
    发票保修(40,"发票保修");

    QuizTypeEnum(Integer code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    private String remark;
    private Integer code;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getRemark() {
        return remark;
    }
}
