package com.gs.lshly.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 10:待审核,20:已通过,30:已驳回
 *
 * 
 * @author yingjun
 * @date 2021年6月17日 下午3:50:04
 */
public enum MarketPtSeckillGoodsSkuStateEnum implements EnumMessage{
	待审核(10,"待审核"),
	已通过(20,"已通过"),
	已驳回(30,"已驳回"),
	;
    private Integer code;
    private String remark;

    MarketPtSeckillGoodsSkuStateEnum(Integer code, String remark) {
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

        for (MarketPtSeckillGoodsSkuStateEnum marketPtSeckillStatusEnum : MarketPtSeckillGoodsSkuStateEnum.values()) {
            if (code.equals(marketPtSeckillStatusEnum.getCode())) {
                return marketPtSeckillStatusEnum.remark;
            }
        }
        return StringUtils.EMPTY;
    }
}
