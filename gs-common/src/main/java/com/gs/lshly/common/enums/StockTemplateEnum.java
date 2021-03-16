package com.gs.lshly.common.enums;

public enum StockTemplateEnum  implements EnumMessage {
    启用(10,"启用"),
    停用(20,"停用");


    private  Integer code;
    private  String remark;
    StockTemplateEnum(Integer code, String remark){
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
