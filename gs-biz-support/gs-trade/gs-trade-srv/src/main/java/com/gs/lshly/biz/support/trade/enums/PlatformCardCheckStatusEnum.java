package com.gs.lshly.biz.support.trade.enums;


import com.gs.lshly.common.enums.EnumMessage;
import com.gs.lshly.common.enums.ShopTypeEnum;

public enum PlatformCardCheckStatusEnum implements EnumMessage {
//平台审核状态[10=待审 20=通过 30=拒审]
    待审(10, "未审核"),
    通过(20, "审核通过"),
    拒审(30, "审核未通过"),
    ;


    PlatformCardCheckStatusEnum(Integer code, String remark){
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
    public static Integer findCode(String remark){
        //values()方法得到所有的枚举元素
        for(PlatformCardCheckStatusEnum s:PlatformCardCheckStatusEnum.values()){
            if(s.remark.equals(remark)){
                return s.code;
            }
        }
        return -1;//-1代表没找到
    }
    //根据学号寻找名字
    public static String findRemark(Integer code){
        //values()方法得到所有的枚举元素
        for(PlatformCardCheckStatusEnum s:PlatformCardCheckStatusEnum.values()){
            if(s.code==code){
                return s.remark;
            }
        }
        return "找不到";
    }
}
