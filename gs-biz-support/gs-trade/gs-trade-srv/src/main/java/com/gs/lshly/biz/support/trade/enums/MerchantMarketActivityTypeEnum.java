package com.gs.lshly.biz.support.trade.enums;


import com.gs.lshly.common.enums.EnumMessage;

public enum MerchantMarketActivityTypeEnum implements EnumMessage {
//@ApiModelProperty("查询类型[10=不可参与 20=可参与未报名 30=可参与已报名 40=报名已结束未报名 50=报名已结束已报名 60=未开始报名]")
    不可参与(10, "不可参与"),
    可参与未报名(20, "可参与(未报名)"),
    可参与已报名(30, "可参与(已报名)"),
    报名已结束未报名(40, "报名已结束(未报名)"),
    报名已结束已报名(50, "报名已结束(已报名)"),
    未开始报名(60, "未开始报名");


    MerchantMarketActivityTypeEnum(Integer code, String remark){
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
