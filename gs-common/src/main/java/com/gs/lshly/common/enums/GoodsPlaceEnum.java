package com.gs.lshly.common.enums;

/**
 * @Author zst
 * @Date 13:29 2021/1/4
 */
public enum GoodsPlaceEnum implements EnumMessage {
    电信专区(10, "电信专区"),
    IN会员专区(20, "IN会员专区"),
    电信国际(30, "电信国际"),
    秒杀专区(40, "秒杀专区"),
    扶贫专区(50, "扶贫专区");

    private Integer code;
    private String remark;

    GoodsPlaceEnum(Integer code, String remark) {
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
