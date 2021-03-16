package com.gs.lshly.common.enums.merchant;


import com.gs.lshly.common.enums.EnumMessage;

/**
 * 店铺地区配置
 */
public enum CardTerminalTypeEnum implements EnumMessage {
//适用平台枚举类型[10=pc端 20=wap端 30=app端]
    pc端(10, "pc端"),
    wap端(20, "wap端"),
    app端(30, "app端");


    CardTerminalTypeEnum(Integer code, String remark){
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
