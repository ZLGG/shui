package com.gs.lshly.common.enums;

/**
 * 关联电信API入口 10:b2i;  20:统一支付；30:bss3.0；40:itv；50:信天游
 *
 * 
 * @author yingjun
 * @date 2021年6月9日 下午6:43:35
 */
public enum GoodsCtccApiEnum implements EnumMessage{
    B2I(10, "B2I"),
    统一支付(20, "统一支付"),
    BSS30(30, "BSS30"),
    ITV(40, "ITV"),
    信天游(50, "信天游");


    GoodsCtccApiEnum(Integer code, String remark){
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
