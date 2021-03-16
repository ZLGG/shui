package com.gs.lshly.common.enums.commondity;

import com.gs.lshly.common.enums.EnumMessage;

/**
 * @Author Starry
 * @Date 18:55 2021/2/24
 */
public enum GoodsIsReleaseEnum implements EnumMessage{
    未发布(10,"未发布"),
    已发布(20,"已发布");

    private Integer code;
    private String remark;

    GoodsIsReleaseEnum(Integer code, String remark) {
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
