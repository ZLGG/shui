package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 13:58 2020/10/22
 */
public enum CompareTypeEnum  implements EnumMessage{
    大于(10, "大于"),
    等于(20, "等于"),
    小于(30,"小于");


    CompareTypeEnum(Integer code, String remark){
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
