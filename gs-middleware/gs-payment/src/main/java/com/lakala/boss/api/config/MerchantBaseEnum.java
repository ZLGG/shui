package com.lakala.boss.api.config;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-zf-ss-onlinepay-sdk
 * @Package com.lakala.boss.api.demo
 * @Description: 请求地址&商户基本信息
 * @date Date : 2019年10月15日 09:37
 */
public enum MerchantBaseEnum {



    merchant_test_Id("872290021025000", "商户编号(测试商家号)"),
    merchant_test_CertPath("872290021025000.p12", "UAT2本地证书路径"),
    merchant_test_CertPass("090857", "UAT2证书密码"),

    merchant_hly_Id("872290021025000", "UAT2商户编号(好粮油测试商家号)"),
    merchant_hly_CertPath("872290021025000.p12","UAT2本地证书路径"),
    merchant_hly_CertPass("090857", "UAT2证书密码"),

//    merchant_hly_Id("872821001035000", "UAT2商户编号(好粮油正式商家号)"),
//    merchant_hly_CertPath("872821001035000.p12","UAT2本地证书路径"),
//    merchant_hly_CertPass("389234", "UAT2证书密码"),

    rootCertPath("rootca.cer", "UAT2服务器根证书"),
    serverUrl("https://intpay.lakala.com/mrpos/cashier", "正式地址"),
    //serverUrl("https://test.wsmsd.cn/uat2/boss/mrpos/cashier", "UAT请求地址"),

    serverCapsUrlDev("http://127.0.0.1:9001/api/entry", "UAT2请求地址"),
    serverCapsUrlUat("http://10.177.89.158/api/entry", "UAT2请求地址");

    private String value;
    private String msg;

    MerchantBaseEnum(String value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
