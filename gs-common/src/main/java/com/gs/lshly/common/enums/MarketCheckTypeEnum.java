package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 16:07 2020/10/16
 * 是否匿名咨询
 */
public enum MarketCheckTypeEnum implements EnumMessage{
//[10=优惠卷 20=团购 30=满减 40=满赠 50=满折 60=活动]
    优惠卷(10,"优惠卷"),
    团购(20,"团购"),
    满减(30,"满减"),
    满赠(40,"满赠"),
    满折(50,"满折"),
    活动(60,"活动");

    private Integer code;
    private String remark;

    MarketCheckTypeEnum(Integer code, String remark) {
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
