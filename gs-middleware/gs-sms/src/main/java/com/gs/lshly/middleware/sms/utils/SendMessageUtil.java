package com.gs.lshly.middleware.sms.utils;

import java.util.Random;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.gs.lshly.common.struct.sms.MessageReq;

 
/**
 * 
 *
 * 
 * @author yingjun
 * @date 2020年6月22日 下午8:26:14
 */
public class SendMessageUtil {
    static final String product = "Dysmsapi";
    static final String domain = "dysmsapi.aliyuncs.com";
    static final String accessKeyId = "LTAI4GKLW1vHaGQokUZdtWpF";
    static final String accessKeySecret = "5L0ZLEdPgtdvYUj9a4Z2BaokTJ2Dd2";
 
    
    public static SendSmsResponse sendSms(MessageReq message) throws ClientException {
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(message.getMobile());
        request.setSignName(message.getSignName());
        request.setTemplateCode(message.getTemplateCode());
        request.setTemplateParam(message.getTemplateParam());
        SendSmsResponse sendSmsResponse = (SendSmsResponse) acsClient.getAcsResponse(request);
        return sendSmsResponse;
    }
 
    public static String getSixNum() {
        String str = "0123456789";
        StringBuilder sb = new StringBuilder(4);
 
        for (int i = 0; i < 6; ++i) {
            char ch = str.charAt((new Random()).nextInt(str.length()));
            sb.append(ch);
        }
 
        return sb.toString();
    }
 
    
}
