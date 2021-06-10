package com.gs.lshly.common.enums;

/**
 * @author OY
 */
public enum TradeCancelReasonTypeEnum implements EnumMessage {

	不想要或多拍了(10, "不想要或多拍了"),
	商品信息拍错(20, "商品信息拍错(规格/尺寸/颜色等)"),
    地址电话信息填写错误(30, "地址/电话信息填写错误"),
    没用或少用优惠券(40, "没用或少用优惠券"),
    协商退款一致(50, "协商退款一致"),
    缺货(60, "缺货"),
    超时未支付(70, "超时未支付"),
    其他(100, "缺货"),
    ;


    TradeCancelReasonTypeEnum(Integer code, String remark){
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
