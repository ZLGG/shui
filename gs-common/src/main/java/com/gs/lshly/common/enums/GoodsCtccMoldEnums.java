package com.gs.lshly.common.enums;

/**
 * 电信商品类型
 *
 * 
 * @author yingjun
 * @date 2021年6月3日 下午10:35:08
 */
public enum GoodsCtccMoldEnums implements EnumMessage{
    普通商品(10, "普通商品"),
    积分商品(20, "积分商品"),
    IN会员商品(30, "IN会员商品"),
    定向积分商品(40, "定向积分商品");


    GoodsCtccMoldEnums(Integer code, String remark){
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
