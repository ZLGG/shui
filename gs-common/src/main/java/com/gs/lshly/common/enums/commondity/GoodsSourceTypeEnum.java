package com.gs.lshly.common.enums.commondity;

import com.gs.lshly.common.enums.EnumMessage;
import com.gs.lshly.common.enums.TradePayTypeEnum;

/**
 * 商品来源枚举
 *
 * @Author shinl
 * @Date 09:55 2021/3/18
 */
public enum GoodsSourceTypeEnum implements EnumMessage{

    商城商品(1,"商城商品"),
    积分商品(2,"积分商品");

    private Integer code;
    private String remark;

    GoodsSourceTypeEnum(Integer code, String remark) {
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
