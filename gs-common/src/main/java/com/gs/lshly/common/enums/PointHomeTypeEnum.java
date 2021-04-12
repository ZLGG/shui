package com.gs.lshly.common.enums;

/**
 * 首页分类
 *
 * 
 * @author yingjun
 * @date 2021年4月12日 上午12:29:56
 */
public enum PointHomeTypeEnum {

	分类("category",1,"分类"),
	广告位("banner",2,"广告位"),
	菜单("menu",3,"菜单"),
	电信产品("ctccProduct",4,"电信产品"),
	秒杀("seckill",5,"秒杀"),
	电信国际("ctccInternational",6,"电信国际"),
	IN会员专区("inMember",7,"IN会员专区"),
	心选好礼("chooseRight",8,"心选好礼"),
	本地生活("localLife",9,"本地生活"),
	精打细算("careful",10,"精打细算");
	
    PointHomeTypeEnum(String code,Integer idx, String remark){
        this.code = code;
        this.idx = idx;
        this.remark = remark;
    }

    private String code;
    
    private Integer idx;

    private String remark;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}
    
}
