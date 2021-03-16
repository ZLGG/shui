package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 14:12 2020/10/17
 */
public enum GoodsQaReplyStateEnum implements EnumMessage{
    未回复(10,"未回复"),
    已回复(20,"已回复");

    private Integer code;
    private String remark;

    GoodsQaReplyStateEnum(Integer code, String remark) {
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
