package com.gs.lshly.common.enums;

import com.gs.lshly.common.enums.EnumMessage;

/**
 * @Author Starry
 * @Date 16:07 2020/10/16
 * 秒杀活动
 */
public enum MarketPtSeckillApplyTypeEnum implements EnumMessage {
    已报名(10, "已报名"),
    去报名(20, "去报名"),
    已结束(50, "已结束");
    private Integer code;
    private String remark;

    MarketPtSeckillApplyTypeEnum(Integer code, String remark) {
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

    public static String getRemarkByCode(Integer code) {

        for (MarketPtSeckillApplyTypeEnum marketPtSeckillApplyTypeEnum : MarketPtSeckillApplyTypeEnum.values()) {
            if (code.equals(marketPtSeckillApplyTypeEnum.getCode())) {
                return marketPtSeckillApplyTypeEnum.remark;
            }
        }
        return MarketPtSeckillApplyTypeEnum.去报名.remark;
    }
}
