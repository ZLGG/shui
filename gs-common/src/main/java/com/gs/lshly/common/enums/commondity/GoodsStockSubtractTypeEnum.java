package com.gs.lshly.common.enums.commondity;

import com.gs.lshly.common.enums.EnumMessage;

/**
 * 商品库存扣减方式
 * @author lxus
 * @since 2020-11-21
 *
 */
public enum GoodsStockSubtractTypeEnum implements EnumMessage{

    支付减库存(10,"支付减库存"),
    下单减库存(20,"下单减库存");

    private Integer code;
    private String remark;

    GoodsStockSubtractTypeEnum(Integer code, String remark) {
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
