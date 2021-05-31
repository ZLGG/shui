package com.gs.lshly.common.enums;

/**
 * 
 *
 * 优惠券类型（1-IN会员抵扣券 2-店铺券 3-平台券 4-个人券）
 * @author yingjun
 * @date 2021年5月28日 下午5:37:22
 */
public enum GoodsCouponTypeEnum implements EnumMessage  {
	IN会员抵扣券(1,"IN会员抵扣券"),
	店铺券(2,"店铺券"),
	平台券(3,"平台券"),
    个人券(4,"个人券")
    ;

    private Integer code;
    private String remark;

    GoodsCouponTypeEnum(Integer code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getRemark() {
        return remark;
    }
    
    public static String getRemarkByCode(Integer code){
    	
    	for (GoodsCouponTypeEnum goodsCouponStatusEnum : GoodsCouponTypeEnum.values()) {
    		if(code.equals(goodsCouponStatusEnum.getCode())){
    			return goodsCouponStatusEnum.remark;
    		}
    	}
    	return GoodsCouponTypeEnum.IN会员抵扣券.remark;
    }
    
}
