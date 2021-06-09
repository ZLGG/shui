package com.gs.lshly.common.enums;

/**
 * @author OY
 */
public enum TradeRightsEndStateEnum implements EnumMessage {

    待处理(10, "待处理"), 商家同意(20, "商家同意"), 商户驳回(30, "商户驳回"), 买家二次申诉(40, "买家二次申诉"), 平台同意(50, "平台同意"),
    平台驳回(60, "平台驳回"), 换货完成(70, "换货完成"), 商家确认收货并退款(80, "商家确认收货并退款"),
    用户取消(90, "用户取消"), 用户修改申请(100, "用户修改申请"); /*已发货(90, "已发货"), 确认收货(91, "确认收货"), 用户取消(95, "用户取消"), 完成(99, "完成");*/

    //10:待处理,20:商家同意,30:商户驳回,40:买家二次申诉,50:平台同意,60:平台驳回,70:平台驳回,80:商家确认收货并退款,90:用户取消
    TradeRightsEndStateEnum(Integer code, String remark) {
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

        for (TradeRightsEndStateEnum tradeRightsStateEnum : TradeRightsEndStateEnum.values()) {
            if (code.equals(tradeRightsStateEnum.getCode())) {
                return tradeRightsStateEnum.remark;
            }
        }
        return TradeRightsEndStateEnum.待处理.remark;
    }
}
