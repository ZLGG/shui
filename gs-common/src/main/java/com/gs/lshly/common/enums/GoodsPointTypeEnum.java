package com.gs.lshly.common.enums;

/**
 * 商品积分类型
 *
 * 
 * @author yingjun
 * @date 2021年4月15日 下午2:00:45
 */
public enum GoodsPointTypeEnum implements EnumMessage{
    普通商品(10,"普通商品"),
    积分商品(20,"积分商品"),
    IN会员商品(30,"IN会员商品");

    private Integer code;
    private String remark;

    GoodsPointTypeEnum(Integer code, String remark) {
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
