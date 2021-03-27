package com.gs.lshly.common.enums.ctcc;


import com.gs.lshly.common.enums.EnumMessage;

/**
 * 地市编码表
 *
 * 
 * @author yingjun
 * @date 2021年3月25日 下午5:17:46
 */
public enum RegionCodeEnum implements EnumMessage {

	浙江省(8330000, "浙江省"),
    杭州市(8330100, "杭州市"),
    宁波市(8330200, "宁波市"),
    温州市(8330300, "温州市"),
    嘉兴市(8330400, "嘉兴市"),
    湖州市(8330500, "湖州市"),
    绍兴市(8330600, "绍兴市"),
    金华市(8330700, "金华市"),
    衢州市(8330800, "衢州市"),
    舟山市(8330900, "舟山市"),
    台州市(8331000, "台州市"),
    丽水市(8331100, "丽水市"),
    ;


    RegionCodeEnum(Integer code, String remark){
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
}
