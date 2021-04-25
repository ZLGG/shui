package com.gs.lshly.common.enums;

import com.gs.lshly.common.enums.EnumMessage;

public enum SubjectEnum implements EnumMessage {

    默认(10, "默认"),
    扶贫(20, "扶贫"),
    好粮油(30, "好粮油"),
    推荐专栏(40,"推荐专栏"),
    积分商城(50,"积分商城"),
    电信国际(60,"电信国际"),
    电信产品(70,"电信产品");

    SubjectEnum(Integer code, String remark){
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
