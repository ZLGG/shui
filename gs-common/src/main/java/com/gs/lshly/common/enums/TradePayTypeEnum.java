package com.gs.lshly.common.enums;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author OY
 */
@Getter
public enum TradePayTypeEnum {

    支付扫码(10, "支付扫码", false, null),
    支付宝APP(20, "支付宝APP", false, null),
    微信扫码(30, "微信扫码", false, null),
    微信公众号(40, "微信公众号", false, null),
    微信APP支付(50, "微信APP支付", false, null),
    微信小程序支付(60, "微信小程序支付", false, null),
    银联PC(70, "银联PC", false, null),
    银联移动(80, "银联移动", false, null),
    线下支付(90, "线下支付", false, null),
    积分支付(100, "积分支付", false, null),

    混合支付积分_微信小程序(510, "混合支付积分_微信小程序", true, TradePayTypeEnum.微信小程序支付);

    TradePayTypeEnum(Integer code, String remark, Boolean mixedPayment, TradePayTypeEnum subType) {
        this.code = code;
        this.remark = remark;
        this.mixedPayment = mixedPayment;
        this.subType = subType;
    }

    private Integer code;

    private String remark;

    /**
     * 是否是混合支付
     */
    private Boolean mixedPayment;

    /**
     * 混合支付的子类型
     */
    private TradePayTypeEnum subType;

    public static TradePayTypeEnum getEnum(Integer code) {
        TradePayTypeEnum[] values = values();
        for (TradePayTypeEnum value : values) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return 支付扫码;
    }

    public static TradePayTypeEnum getEnum(String name) {
        TradePayTypeEnum[] values = values();
        for (TradePayTypeEnum value : values) {
            if (value.name().equals(name)) {
                return value;
            }
        }
        return 支付扫码;
    }
}
