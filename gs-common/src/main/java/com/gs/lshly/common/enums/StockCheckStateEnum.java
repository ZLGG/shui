package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 16:07 2020/10/16
 * 是否匿名咨询
 */
public enum StockCheckStateEnum implements EnumMessage{
    库存项失败(10,"没有库存项"),
    库存正常(20,"库存正常"),
    库存不足(30,"库存不足");

    private Integer code;
    private String remark;

    StockCheckStateEnum(Integer code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    public static String remark(Integer checkState) {
        for (StockCheckStateEnum enums : StockCheckStateEnum.values()) {
            if (enums.code.equals(checkState)) {
                return enums.remark;
            }
        }
        return "";
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
