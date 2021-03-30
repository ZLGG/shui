package com.gs.lshly.common.enums;

/**
 * @Author yangxi
 * @create 2021/3/26 15:38
 * in会员优惠券
 */
public enum InUserCouponPriceEnum implements EnumMessage{

    二十抵扣券(20,"20元"),
    三十抵扣券(30,"30元"),
    五十抵扣券(50,"50元"),
    九十九抵扣券(99,"99元"),
    二百抵扣券(200,"200元"),
    ;

    private Integer code;
    private String remark;

    InUserCouponPriceEnum(Integer code, String remark) {
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
