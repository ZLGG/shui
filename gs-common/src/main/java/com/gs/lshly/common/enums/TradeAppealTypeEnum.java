package com.gs.lshly.common.enums;

/**
 * @author OY
 */
public enum TradeAppealTypeEnum implements EnumMessage {

    删除评论(10, "删除评论"),
    重新评论(20, "重新评论");


    TradeAppealTypeEnum(Integer code, String remark){
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
