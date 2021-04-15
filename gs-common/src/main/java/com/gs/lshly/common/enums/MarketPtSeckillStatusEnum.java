package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 16:07 2020/10/16
 * 秒杀活动
 * 状态 10抢购中 20 已开抢 30即将开抢 40 昨日已开抢
 */
public enum MarketPtSeckillStatusEnum implements EnumMessage{
	抢购中(10,"抢购中"),
	已开抢(20,"已开抢"),
	昨日已开抢(40,"昨日已开抢"),
	即将开抢(30,"即将开抢");
    private Integer code;
    private String remark;

    MarketPtSeckillStatusEnum(Integer code, String remark) {
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
