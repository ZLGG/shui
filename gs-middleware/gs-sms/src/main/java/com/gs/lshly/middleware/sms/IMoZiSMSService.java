package com.gs.lshly.middleware.sms;


import java.util.Map;

/**
 * 摩字短信服务
 * @author zst
 * @since 2021/2/23
 */
public interface IMoZiSMSService {

    //内容发送接口
    String sendTemplate(String mobiles, String sign, String content, String merchantOrderId);
    //模板发送接口
    String TemplateDate(String mobiles, String tplId, Map<String,Object> parameter, String merchantOrderId);
    //余额查询接口
    String balanceQueryDate(String merchantId);

}
