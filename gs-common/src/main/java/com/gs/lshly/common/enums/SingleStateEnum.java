package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 17:56 2020/10/24
 */
public enum SingleStateEnum implements EnumMessage{

    单品(10, "单品"),
    多规格(20, "多规格");


    SingleStateEnum(Integer code, String remark){
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
