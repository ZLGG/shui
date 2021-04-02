package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 19:49 2021/3/8
 */
public enum MerchantApplyTypeEnum implements EnumMessage{
    入驻前申请提交(10, "入驻前申请提交"),
    入驻后修改信息提交(20, "入驻后修改信息提交");

    MerchantApplyTypeEnum(Integer code, String remark){
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
