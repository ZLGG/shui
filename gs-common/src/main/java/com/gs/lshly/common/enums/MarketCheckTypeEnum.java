package com.gs.lshly.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Starry
 * @Date 16:07 2020/10/16
 * 是否匿名咨询
 */
public enum MarketCheckTypeEnum implements EnumMessage{
	//[10=优惠卷 20=团购 30=满减 40=满赠 50=满折 60=活动 70秒杀]
    优惠卷(10,"优惠卷"),
    团购(20,"团购"),
    满减(30,"满减"),
    满赠(40,"满赠"),
    满折(50,"满折"),
    活动(60,"活动"),
    秒杀(70,"秒杀");

    private Integer code;
    private String remark;

    MarketCheckTypeEnum(Integer code, String remark) {
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
        for (MarketCheckTypeEnum marketCheckTypeEnum : MarketCheckTypeEnum.values()) {
            map = new HashMap<String,Object>();
            map.put("id",marketCheckTypeEnum.getCode());
            map.put("name",marketCheckTypeEnum.getRemark());
            typeList.add(map);
        }
        return typeList;
    }
    
    public static String getRemarkByCode(Integer code){
    	
    	for (MarketCheckTypeEnum marketCheckTypeEnum : MarketCheckTypeEnum.values()) {
    		if(code.equals(marketCheckTypeEnum.getCode())){
    			return marketCheckTypeEnum.remark;
    		}
    	}
    	return MarketCheckTypeEnum.活动.remark;
    }
    
    
}
