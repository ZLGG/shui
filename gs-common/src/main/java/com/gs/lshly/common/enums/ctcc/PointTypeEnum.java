package com.gs.lshly.common.enums.ctcc;

/**
 * 积分类型组码表
 *
 * 
 * @author yingjun
 * @date 2021年3月25日 下午1:59:52
 */
public enum PointTypeEnum {

	电信产品积分("6001040201", "电信产品积分"),
	政企关键人积分("6001040202", "政企关键人积分"),
	电信产品积分和政企关键人积分("6001040204", "电信产品积分和政企关键人积分"),
	权益("6001040501", "权益"),
    ;
	
    PointTypeEnum(String code, String remark){
        this.code = code;
        this.remark = remark;
    }

    private String code;

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

    
}
