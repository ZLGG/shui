package com.gs.lshly.common.enums;

public enum SkuActivityPriceEnum implements EnumMessage{

    //Sku活动价枚举类型[10=SKU促销价 20=此商品无sku值]
    SKU促销价(10,"SKU促销价"),
    此商品无sku值(20,"此商品无sku值");


    SkuActivityPriceEnum(Integer code, String remark){
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
