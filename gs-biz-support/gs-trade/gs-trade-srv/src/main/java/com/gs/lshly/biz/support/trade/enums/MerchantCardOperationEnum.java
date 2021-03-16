package com.gs.lshly.biz.support.trade.enums;


import com.gs.lshly.common.enums.EnumMessage;

public enum MerchantCardOperationEnum implements EnumMessage {
//操作枚举类型[10=未审核 20=可领取 30=待生效 40=已取消 50=结束领取 60=生效中 70=待领取 80=已失效 90=审核拒绝]
    未审核(10, "未审核"),
    可领取(20, "可领取"),
    待生效(30, "待生效"),
    已取消(40, "已取消"),
    结束领取(50, "结束领取"),
    生效中(60, "生效中"),
    待领取(70,"待领取"),
    已失效(80,"已失效"),
    审核拒绝(90,"审核拒绝");


    MerchantCardOperationEnum(Integer code, String remark){
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
