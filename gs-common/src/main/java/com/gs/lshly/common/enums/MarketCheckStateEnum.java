package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 16:07 2020/10/16
 * 是否匿名咨询
 */
public enum MarketCheckStateEnum implements EnumMessage{
//[10=待审 20=通过 30=拒审]
    待审(10,"待审"),
    通过(20,"通过"),
    拒审(30,"拒审");
    private Integer code;
    private String remark;

    MarketCheckStateEnum(Integer code, String remark) {
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
