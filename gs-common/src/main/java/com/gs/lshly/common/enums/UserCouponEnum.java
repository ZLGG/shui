package com.gs.lshly.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author yangxi
 * @create 2021/5/27 16:39
 */

public enum UserCouponEnum implements EnumMessage {

    IN会员抵扣券(1, "IN会员抵扣券"),
    店铺券(2, "店铺券"),
    平台券(3, "平台券"),
    个人券(4, "个人券"),
    ;

    private Integer code;
    private String remark;

    UserCouponEnum(Integer code, String remark) {
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


    public static List<Map<String, Object>> getAll() {
        Map<String, Object> map = null;
        List<Map<String, Object>> typeList = new ArrayList<Map<String, Object>>();
        for (com.gs.lshly.common.enums.InUserCouponPriceEnum couponEnum : com.gs.lshly.common.enums.InUserCouponPriceEnum.values()) {
            map = new HashMap<String, Object>();
            map.put("id", couponEnum.getCode());
            map.put("name", couponEnum.getRemark());
            typeList.add(map);
        }
        return typeList;
    }
}
