package com.gs.lshly.common.enums;

/**
 * @author zst
 */
public enum TradeReportTypeEnum implements EnumMessage {

    新增订单金额(10, "新增订单金额"),
    新增订单数量(20, "新增订单数量"),
    平均单价(30, "平均单价"),
    已取消订单金额(40, "已取消订单金额"),
    已取消订单数量(50, "已取消订单数量"),;

    TradeReportTypeEnum(Integer code, String remark){
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
