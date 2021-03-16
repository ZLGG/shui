package com.gs.lshly.common.enums;

/**
 * 是否显示原价
 * @Author Starry
 * @Date 17:39 2020/10/13
 */
public enum ShowOldPriceEnum implements EnumMessage{
    显示原价(10, "是"),
    不显示原价(20, "否");


    ShowOldPriceEnum(Integer code, String remark){
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
