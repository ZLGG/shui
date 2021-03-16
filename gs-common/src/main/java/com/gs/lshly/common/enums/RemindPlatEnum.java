package com.gs.lshly.common.enums;

public enum RemindPlatEnum implements EnumMessage{
    /**
     * 企业会员待审核  = 10
     * 商家入驻待审核  = 11
     * 商品上架待审核  = 12
     * 申请类目待审核  = 13
     * 商家文章待审核  = 14
     * 商家营销活动待审核 = 15
     * 商家报名活动待审核 = 16
     * 评论申诉待审核 = 17
     * 订单投诉待审核 = 18
     * 售后申请待审核 = 19
     * 退款待审核 = 20
     * 商家意见反馈待审核 = 21
     * 会员意见反馈提醒 = 22
     * 转账待审核 = 23
     */
    企业会员待审核(11,"企业会员待审核"),
    商家入驻待审核(12,"商家入驻待审核"),
    商品上架待审核(13,"商品上架待审核"),
    申请类目待审核(14,"申请类目待审核"),
    商家文章待审核(15,"商家文章待审核"),
    商家营销活动待审核(16,"商家营销活动待审核"),
    评论申诉待审核(17,"评论申诉待审核"),
    订单投诉待审核(18,"订单投诉待审核"),
    售后申请待审核(19,"售后申请待审核"),
    退款待审核(20,"退款待审核"),
    商家意见反馈待审核(21,"商家意见反馈待审核"),
    会员意见反馈提醒(22,"会员意见反馈提醒"),
    转账待审核(23,"转账待审核");

    RemindPlatEnum(Integer code, String remark){
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
