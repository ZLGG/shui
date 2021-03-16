package com.gs.lshly.common.enums.merchant;

import com.gs.lshly.common.enums.EnumMessage;

/**
 * @Author Starry
 * @Date 15:32 2021/2/25
 */
public enum LegalPersonTypeEnum implements EnumMessage {
    中国大陆(10, "中国大陆"),
    非中国大陆(20, "非中国大陆");


    LegalPersonTypeEnum(Integer code, String remark){
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
