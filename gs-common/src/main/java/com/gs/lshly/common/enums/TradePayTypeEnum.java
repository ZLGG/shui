package com.gs.lshly.common.enums;

/**
 * @author OY
 */
public enum TradePayTypeEnum implements EnumMessage {

    支付扫码(10, "支付扫码"),
    支付宝APP(20, "支付宝APP"),
    微信扫码(30, "微信扫码"),
    微信公众号(40, "微信公众号"),
    微信APP支付(50, "微信APP支付"),
    微信小程序支付(60, "微信小程序支付"),
    银联PC(70, "银联PC"),
    银联移动(80, "银联移动"),
    线下支付(90, "线下支付");

    TradePayTypeEnum(Integer code, String remark){
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
