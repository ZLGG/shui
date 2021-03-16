package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 16:07 2020/10/16
 *
 */
public enum AdvertTypeEnum implements EnumMessage{

    通栏广告(10,"通栏广告"),
    单张广告(20,"单张广告"),
    组合广告(30,"组合广告");

    private Integer code;
    private String remark;

    AdvertTypeEnum(Integer code, String remark) {
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
