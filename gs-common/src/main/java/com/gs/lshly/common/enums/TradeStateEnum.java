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
 * @date 2021年6月18日 下午5:39:11
 */
public enum TradeStateEnum implements EnumMessage {

    待支付(10, "待支付"),
    待发货(20, "待发货"),
    待收货(30, "待收货"),
    已完成(40, "已完成"),
    已取消(50, "已取消"),
    待评价(60, "待评价"),
    售后处理中(70, "售后处理中"),
    关闭(100, "关闭"),
    ;


    TradeStateEnum(Integer code, String remark){
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
        for (TradeStateEnum tradeStateEnum : TradeStateEnum.values()) {
            map = new HashMap<String,Object>();
            map.put("id",tradeStateEnum.getCode());
            map.put("name",tradeStateEnum.getRemark());
            typeList.add(map);
        }
        return typeList;
    }
    
    public static String getRemarkByCode(Integer code){
    	
    	for (TradeStateEnum tradeStateEnum : TradeStateEnum.values()) {
    		if(code.equals(tradeStateEnum.getCode())){
    			return tradeStateEnum.remark;
    		}
    	}
    	return TradeStateEnum.待支付.remark;
    }
}
