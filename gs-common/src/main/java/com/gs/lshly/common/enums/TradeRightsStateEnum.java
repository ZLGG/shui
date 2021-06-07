package com.gs.lshly.common.enums;

/**
 * @author OY
 */
public enum TradeRightsStateEnum implements EnumMessage {

    申请(10, "申请"), 驳回(20, "驳回"), 通过(30, "通过"), 已退货(40, "已退货") , 收到退货(50, "收到退货"), 等待退款(60, "等待退款"), 退款完成(70,
			"退款完成"), 等待发货(80, "等待发货"), 已发货(90, "已发货"), 确认收货(91, "确认收货"), 用户取消(95, "用户取消"), 完成(99, "完成");

    TradeRightsStateEnum(Integer code, String remark) {
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

    public static String getRemarkByCode(Integer code) {

        for (TradeRightsStateEnum tradeRightsStateEnum : TradeRightsStateEnum.values()) {
            if (code.equals(tradeRightsStateEnum.getCode())) {
                return tradeRightsStateEnum.remark;
            }
        }
        return TradeRightsStateEnum.申请.remark;
    }
}
