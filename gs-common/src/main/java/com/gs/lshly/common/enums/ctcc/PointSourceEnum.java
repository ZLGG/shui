package com.gs.lshly.common.enums.ctcc;


import com.gs.lshly.common.enums.EnumMessage;

/**
 * 积分来源系统编码
 *
 * 
 * @author yingjun
 * @date 2021年3月25日 下午5:17:46
 */
public enum PointSourceEnum implements EnumMessage {

	省积分商城(600001, "省积分商城"),
    省电渠(600002, "省电渠"),
    一万号(600003, "10000"),
    爱维系(600004, "爱维系"),
    营销支撑系统(600006, "营销支撑系统"),
    统一支付(600007, "统一支付"),
    客调(600008, "客调"),
    受理中心(600009, "受理中心"),
    ITV(6000010, "ITV"),
    isee(6000011, "isee"),
    微信小程序(6000012, "微信小程序"),
    ;


    PointSourceEnum(Integer code, String remark){
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
