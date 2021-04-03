package com.gs.lshly.common.enums.ctcc;


import com.gs.lshly.common.enums.EnumMessage;

/**
 * 客户星级
 *
 * 
 * @author yingjun
 * @date 2021年3月25日 下午5:17:46
 */
public enum CustomerStarEnum implements EnumMessage {

	星1(3100, "1星"),
    星2(3200, "2星"),
    星3(3300, "3星"),
    星4(3400, "4星"),
    星5(3500, "5星"),
    星6(3600, "6星"),
    星7(3700, "7星"),
    星0(3800, "0星"),
    ;


    CustomerStarEnum(Integer code, String remark){
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
