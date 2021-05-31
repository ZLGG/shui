package com.gs.lshly.common.enums;

/**
 * @author OY
 */
public enum TradeRightsReasonTypeEnum implements EnumMessage {

    不想要或多拍了(10, "不想要或多拍了"),
    商品信息拍错规格尺码颜色等(25, "商品信息,拍错(规格/尺码/颜色)等"),
    地址电话信息填写错误(20, "地址信息填写错误"),
    没用或少用优惠券(25, "没用或少用优惠券"),
    协商退款一致(30, "协商退款一致"),
    缺货(35, "缺货"),
    其他(40, "其他"),
    与商品详情不符(45, "与商品详情不符"),
    生产日期保质期与商品不符(50, "生产日期/保质期与商品不符"),
    图片产地规格等描述不符(55, "图片/产地/规格等描述不符他"),
    商品变质(60, "商品变质"),
    商品破损缺少配件(65, "商品破损/缺少配件"),
    卖家发错货(70, "其他"),
    不喜欢不想要(75, "不喜欢/不想要"),
    空包裹(80, "空包裹"),
    快递一直未送达(85, "快递一直未送达"),
    快递无跟踪记录(90, "快递无跟踪记录"),
    货物破损已拒签(95, "其货物破损已拒签他"),
    取消订单(100, "取消订单");

    TradeRightsReasonTypeEnum(Integer code, String remark) {
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
