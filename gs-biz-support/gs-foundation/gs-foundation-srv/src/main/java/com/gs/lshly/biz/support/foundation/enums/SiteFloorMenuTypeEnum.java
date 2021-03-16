package com.gs.lshly.biz.support.foundation.enums;

import com.gs.lshly.common.enums.EnumMessage;

public enum SiteFloorMenuTypeEnum implements EnumMessage {

    楼层顶部(10, "楼层顶部"),
    左侧链接(20, "左侧链接");


    SiteFloorMenuTypeEnum(Integer code, String remark){
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
