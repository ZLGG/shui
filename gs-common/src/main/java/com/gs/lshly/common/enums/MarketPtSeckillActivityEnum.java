package com.gs.lshly.common.enums;

/**
 * @Author hanly
 * @Date 18:10 2021/06/01
 */
public enum MarketPtSeckillActivityEnum implements EnumMessage{
    未开始(10, "未开始"),
    进行中(20, "进行中"),
    已结束(30, "已结束");


    MarketPtSeckillActivityEnum(int code, String remark) {
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
