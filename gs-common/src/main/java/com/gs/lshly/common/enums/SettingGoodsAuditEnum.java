package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 10:43 2020/10/30
 */
public enum SettingGoodsAuditEnum implements EnumMessage{
    开启商品上架审核(10, "开启"),
    关闭商品上架审核(20, "关闭");


    SettingGoodsAuditEnum(int code, String remark) {
        this.code =  code;
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
