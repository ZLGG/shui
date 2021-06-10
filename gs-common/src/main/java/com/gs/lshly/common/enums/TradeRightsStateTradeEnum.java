package com.gs.lshly.common.enums;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年6月10日 上午11:29:19
 */
public enum TradeRightsStateTradeEnum implements EnumMessage {
	无售后(0,"无售后"),
    售后中(10, "售后中"), 
    完成售后(20, "完成售后"); 
	
    TradeRightsStateTradeEnum(Integer code, String remark) {
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

    public static String getRemarkByCode(Integer code) {

        for (TradeRightsStateTradeEnum tradeRightsStateEnum : TradeRightsStateTradeEnum.values()) {
            if (code.equals(tradeRightsStateEnum.getCode())) {
                return tradeRightsStateEnum.remark;
            }
        }
        return TradeRightsStateTradeEnum.无售后.remark;
    }
}
