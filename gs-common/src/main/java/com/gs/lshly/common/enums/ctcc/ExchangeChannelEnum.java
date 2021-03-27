package com.gs.lshly.common.enums.ctcc;

/**
 * 兑换渠道编码
 *
 * 
 * @author yingjun
 * @date 2021年3月25日 下午1:59:52
 */
public enum ExchangeChannelEnum {

	网厅("网厅", "网上营业厅"),
	短厅("短厅", "短信营业厅"),
	WAP("WAP", "wap营业厅"),
	积分商城("积分商城", "省内积分商城"),
	IPTV("IPTV", "IPTV平台"),
	微信("微信", "微信公众号"),
	一万号("一万号", "10000号"),
	CRM("CRM", "BSS3.0受理中心"),
    ;
	
    ExchangeChannelEnum(String code, String remark){
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
