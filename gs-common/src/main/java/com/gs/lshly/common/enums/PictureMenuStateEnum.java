package com.gs.lshly.common.enums;


/**
 * @Author Starry
 * @Date 14:11 2020/10/22
 */
public enum PictureMenuStateEnum  implements EnumMessage{
    平台图片(10, "平台图片"),
    店铺图片(20, "店铺图片"),
    商家入驻图片(30,"商家入驻图片"),
    用户图片(40, "用户图片"),
    回收站(50, "回收站");


    PictureMenuStateEnum(Integer code, String remark){
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
