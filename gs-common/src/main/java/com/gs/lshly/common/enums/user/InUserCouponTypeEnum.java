package com.gs.lshly.common.enums.user;

import com.gs.lshly.common.enums.EnumMessage;

/**
 * @Author yangxi
 * @create 2021/3/29 17:04
 */
public enum InUserCouponTypeEnum implements EnumMessage {
    购买赠送(0,"购买会员赠送"),
    分享赠送(1,"会员分享赠送")
    ;

    private Integer code;
    private String remark;

    InUserCouponTypeEnum(Integer code, String remark) {
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
