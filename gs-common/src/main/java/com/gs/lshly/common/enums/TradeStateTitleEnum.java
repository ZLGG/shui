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
public enum TradeStateTitleEnum implements EnumMessage {

    待支付(10, "等待支付"),
    待发货(20, "买家已付款"),
    待收货(30, "卖家已发货"),
    已完成(40, "交易成功"),
    已取消(50, "交易关闭"),
    待评价(60, "已取消"),
    售后处理中(70, "售后处理中"),;


    TradeStateTitleEnum(Integer code, String remark){
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
        for (TradeStateTitleEnum tradeStateEnum : TradeStateTitleEnum.values()) {
            map = new HashMap<String,Object>();
            map.put("id",tradeStateEnum.getCode());
            map.put("name",tradeStateEnum.getRemark());
            typeList.add(map);
        }
        return typeList;
    }
    
    public static String getRemarkByCode(Integer code){
    	
    	for (TradeStateTitleEnum tradeStateEnum : TradeStateTitleEnum.values()) {
    		if(code.equals(tradeStateEnum.getCode())){
    			return tradeStateEnum.remark;
    		}
    	}
    	return TradeStateTitleEnum.待支付.remark;
    }
}
