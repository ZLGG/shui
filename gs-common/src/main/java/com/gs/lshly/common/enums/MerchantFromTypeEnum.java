package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 16:07 2020/10/16
 * 是否匿名咨询
 */
public enum MerchantFromTypeEnum implements EnumMessage{

    平台入驻(10,"平台入驻"),
    平台添加(20,"平台添加");

    private Integer code;
    private String remark;

    MerchantFromTypeEnum(Integer code, String remark) {
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
