package com.gs.lshly.common.enums;

/**
 * @Author yangxi
 * @create 2021/3/29 16:57
 */
public enum UserCouponStatusEnum implements EnumMessage  {
    未使用(0,"未使用"),
    已使用(1,"已使用"),
    已过期(2,"已过期"),
    使用中(3,"使用中"),
    ;

    private Integer code;
    private String remark;

    UserCouponStatusEnum(Integer code, String remark) {
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
