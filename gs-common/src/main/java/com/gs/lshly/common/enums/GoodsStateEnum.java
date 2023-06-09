package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 11:35 2020/10/8
 */
public enum GoodsStateEnum implements EnumMessage{
    未上架(10, "未上架"),
    已上架(20, "已上架"),
    待审核(30, "待审核"),
    已审核(40, "已审核"),
    草稿箱(50, "草稿箱"),
    已审核上架(60, "新增商品-已审核-上架"),
    未上架编辑(70, "未上架商品-编辑");


    GoodsStateEnum(Integer code, String remark){
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
