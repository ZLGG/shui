package com.gs.lshly.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author yangxi
 * @create 2021/3/26 15:38
 * in会员优惠券
 */
public enum InUserCoupon399Enum implements EnumMessage{

    二十抵扣券(20,"20元"),
    三十抵扣券(30,"30元"),
    五十抵扣券(50,"50元"),
    九十九抵扣券(99,"99元"),
    二百抵扣券(200,"200元"),
    ;

    private Integer code;
    private String remark;

    InUserCoupon399Enum(Integer code, String remark) {
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
        for (InUserCoupon399Enum couponEnum : InUserCoupon399Enum.values()) {
            map = new HashMap<String,Object>();
            map.put("id",couponEnum.getCode());
            map.put("name",couponEnum.getRemark());
            typeList.add(map);
        }
        return typeList;
    }
    
    
}
