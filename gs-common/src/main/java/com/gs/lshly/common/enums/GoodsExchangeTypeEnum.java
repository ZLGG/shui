package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 11:35 2020/10/8
 */
public enum GoodsExchangeTypeEnum implements EnumMessage{
    虚拟(10, "虚拟"),
    实物(20, "实物"),;


    GoodsExchangeTypeEnum(Integer code, String remark){
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
