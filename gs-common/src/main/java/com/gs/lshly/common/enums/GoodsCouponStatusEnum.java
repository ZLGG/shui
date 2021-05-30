package com.gs.lshly.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年5月28日 下午5:37:22
 */
public enum GoodsCouponStatusEnum implements EnumMessage  {
    未领取(0,"未领取"),
    已领取(1,"已领取"),
    无需领取(2,"无需领取")
    ;

    private Integer code;
    private String remark;

    GoodsCouponStatusEnum(Integer code, String remark) {
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
    	
    	for (GoodsCouponStatusEnum goodsCouponStatusEnum : GoodsCouponStatusEnum.values()) {
    		if(code.equals(goodsCouponStatusEnum.getCode())){
    			return goodsCouponStatusEnum.remark;
    		}
    	}
    	return GoodsCouponStatusEnum.未领取.remark;
    }
    
}
