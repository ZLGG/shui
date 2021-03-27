package com.gs.lshly.common.enums.ctcc;

/**
 * 权益编码表
 *
 * 
 * @author yingjun
 * @date 2021年3月25日 下午5:17:46
 */
public enum InterestsCodeEnum {

	积分倍增_倍("100100", "积分倍增_倍"),
	优先接入10000号("100200", "优先接入10000号"),
	免费换卡("100300", "免费换卡"),
    宽带紧急复机("100400", "宽带紧急复机"),
    星级客服经理("100500", "星级客服经理"),
    手机紧急开机("100600", "手机紧急开机"),
    宽带上门服务_无线组网("100701", "宽带上门服务_无线组网"),
    宽带上门服务_免费移机("100702", "宽带上门服务_免费移机"),
    宽带上门服务_代装其它终端("100703", "宽带上门服务_代装其它终端"),
    国漫免预存_押金开通("100800", "国漫免预存_押金开通"),
    营业厅优先办理("100900", "国漫免预存_押金开通"),
    生日流量赠送("101102", "国漫免预存_押金开通"),
    返回所有权益("6001040501", "国漫免预存_押金开通"),
    ;


    InterestsCodeEnum(String code, String remark){
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
