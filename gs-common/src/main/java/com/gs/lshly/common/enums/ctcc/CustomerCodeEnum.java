package com.gs.lshly.common.enums.ctcc;

/**
 * 客户识别码
 *
 * 
 * @author yingjun
 * @date 2021年3月25日 下午1:59:52
 */
public enum CustomerCodeEnum {

	手机号码("11", "手机号码"),
    固话号码("12", "固话号码"),
    宽带帐号("13", "宽带帐号"),
    客户编码("6001040101", "宽带帐号"),
    小灵通("17", "宽带帐号"),
    客户统一标识("15", "宽带帐号"),
    客户ID("6001040102", "宽带帐号"),
    产品实例ID("6001040103", "宽带帐号"),
    专线编号("6001040104", "宽带帐号");
	
    CustomerCodeEnum(String code, String remark){
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
