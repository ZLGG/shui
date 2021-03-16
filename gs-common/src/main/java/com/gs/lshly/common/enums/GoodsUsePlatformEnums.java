package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 16:45 2020/10/9
 */
public enum GoodsUsePlatformEnums implements EnumMessage{
    B商城和C商城(10, "2B商城和2C商城"),
    B商城(20, "2B商城"),
    C商城(30, "2C商城");


    GoodsUsePlatformEnums(Integer code, String remark){
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
