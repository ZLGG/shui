package com.gs.lshly.common.enums;

/**
 * 优惠券类型
 * @Author OY
 */
public enum TradeCouponTypeEnum implements EnumMessage{
    普通(10, "普通"),
    IN会员(20, "IN会员");


    TradeCouponTypeEnum(Integer code, String remark){
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
