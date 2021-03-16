package com.gs.lshly.common.enums;
public enum ShopTypeEnum implements EnumMessage {

    品牌旗舰店(10, "品牌旗舰店"),
    品牌专卖店(20, "品牌专卖店"),
    类目专营店(30, "类目专营店"),
    运营商自营(40, "运营商自营"),
    多品类通用(50, "多品类通用");
    ShopTypeEnum(Integer code, String remark){
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
        for(ShopTypeEnum s:ShopTypeEnum.values()){
            if(s.remark.equals(remark)){
                return s.code;
            }
        }
        return -1;//-1代表没找到
    }
    //根据学号寻找名字
    public static String findRemark(Integer code){
        //values()方法得到所有的枚举元素
        for(ShopTypeEnum s:ShopTypeEnum.values()){
            if(s.code==code){
                return s.remark;
            }
        }
        return "找不到";
    }

}
