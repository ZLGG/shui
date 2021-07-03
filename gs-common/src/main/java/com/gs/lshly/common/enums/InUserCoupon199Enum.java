package com.gs.lshly.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 199优惠券
 *
 * 
 * @author yingjun
 * @date 2021年7月3日 下午11:42:06
 */
public enum InUserCoupon199Enum implements EnumMessage{

    二十抵扣券(20,"DYJ000030"),
    三十抵扣券(30,"DYJ000032"),
    五十抵扣券(50,"50元"),
    九十九抵扣券(99,"99元"),
    ;

    private Integer code;
    private String remark;

    InUserCoupon199Enum(Integer code, String remark) {
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
    
    
    public static List<Map<String,Object>> getAll(){
        Map<String,Object> map = null;
        List<Map<String,Object>> typeList = new ArrayList<Map<String,Object>>();
        for (InUserCoupon199Enum couponEnum : InUserCoupon199Enum.values()) {
            map = new HashMap<String,Object>();
            map.put("id",couponEnum.getCode());
            map.put("name",couponEnum.getRemark());
            typeList.add(map);
        }
        return typeList;
    }
    
    
}
