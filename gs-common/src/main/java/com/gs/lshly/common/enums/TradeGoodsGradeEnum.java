package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 20:07 2020/11/17
 */
public enum TradeGoodsGradeEnum implements EnumMessage{
    差评(10,"差评"),
    中评(20,"中评"),
    好评(30,"好评");

    private Integer code;
    private String remark;

    TradeGoodsGradeEnum(Integer code, String remark) {
        this.code = code;
        this.remark = remark;
    }
    public static String get(Integer value){
        TradeGoodsGradeEnum[] values = TradeGoodsGradeEnum.values();
        String enumValue = null;
        for(TradeGoodsGradeEnum eachValue : values) {
            enumValue =eachValue.toString();

            if (enumValue.equals(value)) {
                return eachValue.name();
            }
        }
        return enumValue;
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
