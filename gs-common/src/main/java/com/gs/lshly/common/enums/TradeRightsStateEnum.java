package com.gs.lshly.common.enums;

/**
 * @author OY
 */
public enum TradeRightsStateEnum implements EnumMessage {

    提交申请(10, "提交申请"), 商家处理(20, "商家处理"), 成功(30, "成功"), 驳回(40, "驳回"), 用户取消(40, "用户取消")/*, 收到退货(50, "收到退货"), 等待退款(60, "等待退款"), 退款完成(70,
			"退款完成"), 等待发货(80, "等待发货"), 已发货(90, "已发货"), 确认收货(91, "确认收货"), 用户取消(95, "用户取消"), 完成(99, "完成")*/;

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
        return TradeRightsStateEnum.提交申请.remark;
    }
}
