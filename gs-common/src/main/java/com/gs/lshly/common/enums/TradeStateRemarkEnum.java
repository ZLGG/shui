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
 * @date 2021年5月23日 下午9:43:00
 */
public enum TradeStateRemarkEnum implements EnumMessage {

    待支付(10, ""),
    待发货(20, "您的包裹正在整装待发"),
    待收货(30, "您的包裹已经朝您飞奔过来哦"),
    已完成(40, ""),
    已取消(50, ""),
    待评价(60, ""),
    售后处理中(70, ""),;


    TradeStateRemarkEnum(Integer code, String remark){
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
    
    public static List<Map<String,Object>> getAll(){
        Map<String,Object> map = null;
        List<Map<String,Object>> typeList = new ArrayList<Map<String,Object>>();
        for (TradeStateRemarkEnum tradeStateEnum : TradeStateRemarkEnum.values()) {
            map = new HashMap<String,Object>();
            map.put("id",tradeStateEnum.getCode());
            map.put("name",tradeStateEnum.getRemark());
            typeList.add(map);
        }
        return typeList;
    }
    
    public static String getRemarkByCode(Integer code){
    	
    	for (TradeStateRemarkEnum tradeStateEnum : TradeStateRemarkEnum.values()) {
    		if(code.equals(tradeStateEnum.getCode())){
    			return tradeStateEnum.remark;
    		}
    	}
    	return TradeStateRemarkEnum.待支付.remark;
    }
}
