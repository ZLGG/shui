package com.gs.lshly.common.enums;

/**
 * 商家运费模板计价方式
 * @author zhou
 * @date 17:55 2020/10/10
 */
public enum FreightChargeFreeConditionTypeEnum implements EnumMessage {
    WEIGHT_TYPE(10,"重量条件"),
    NUMBER_TYPE(20,"件数条件"),
    AMOUNT_TYPE(30,"金额条件"),
    AMOUNT_AND_WEIGHT_TYPE(40,"金额And重量条件"),
    AMOUNT_AND_NUMBER_TYPE(50,"金额And件数条件")
    ;

    FreightChargeFreeConditionTypeEnum(int code, String remark){
        this.code = code;
        this.remark = remark;
    }

     int code;
     String remark;

    @Override
    public Integer getCode() {
        return code;
    }
    @Override
    public String getRemark() {
        return remark;
    }
}
