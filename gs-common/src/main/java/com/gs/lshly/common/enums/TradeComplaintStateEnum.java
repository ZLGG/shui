package com.gs.lshly.common.enums;

/**
 * @Author Starry
 * @Date 18:56 2020/12/23
 */
public enum TradeComplaintStateEnum implements EnumMessage{
    全部(-1,"全部"),
    等待处理(10,"等待处理"),
    投诉成功(20,"投诉成功"),
    关闭投诉(30,"关闭投诉"),
    买家撤销了投诉(40,"买家撤销了投诉");

    private Integer code;
    private String remark;

    TradeComplaintStateEnum(Integer code, String remark) {
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
