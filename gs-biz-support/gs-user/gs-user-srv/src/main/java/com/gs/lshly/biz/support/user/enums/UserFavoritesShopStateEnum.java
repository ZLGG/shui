package com.gs.lshly.biz.support.user.enums;


import com.gs.lshly.common.enums.EnumMessage;

public enum UserFavoritesShopStateEnum implements EnumMessage {

    收藏(10, "收藏"),
    取消收藏(20, "取消收藏");


    UserFavoritesShopStateEnum(Integer code, String remark){
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
