package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 16:07 2020/10/16
 * 秒杀时间段
 */
public enum MarketPtSeckillTimeQuantumEnum implements EnumMessage{
    Time_22(22,"22:00"),
    Time_10(10,"10:00"),
    Time_12(12,"12:00"),
    Time_18(18,"18:00"),
    Time_20(20,"20:00"),
    ;
    private Integer code;
    private String remark;

    MarketPtSeckillTimeQuantumEnum(Integer code, String remark) {
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
