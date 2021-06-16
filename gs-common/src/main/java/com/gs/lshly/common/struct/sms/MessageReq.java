package com.gs.lshly.common.struct.sms;

public class MessageReq {

	private String mobile;
    private String signName;
    private String TemplateCode;
    private String TemplateParam;
 
    public String getMobile() {
        return this.mobile;
    }
 
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
 
    public String getSignName() {
        return this.signName;
    }
 
    public void setSignName(String signName) {
        this.signName = signName;
    }
 
    public String getTemplateCode() {
        return this.TemplateCode;
    }
 
    public void setTemplateCode(String templateCode) {
        this.TemplateCode = templateCode;
    }
 
    public String getTemplateParam() {
        return this.TemplateParam;
    }
 
    public void setTemplateParam(String templateParam) {
        this.TemplateParam = templateParam;
    }
 
    public MessageReq(String mobile, String signName, String templateCode, String templateParam) {
        this.mobile = mobile;
        this.signName = signName;
        this.TemplateCode = templateCode;
        this.TemplateParam = templateParam;
    }
 
    public MessageReq() {
    }
}
