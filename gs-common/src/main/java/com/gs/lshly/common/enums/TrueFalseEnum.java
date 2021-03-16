package com.gs.lshly.common.enums;

import java.io.Serializable;

/**
 * 资产类型
 * @author lxus
 * @date 2020/9/11
 */
public enum TrueFalseEnum implements EnumMessage, Serializable {

    是(1, "是"),
    否(0, "否");


    TrueFalseEnum(int code, String remark) {
        this.code =  code;
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
