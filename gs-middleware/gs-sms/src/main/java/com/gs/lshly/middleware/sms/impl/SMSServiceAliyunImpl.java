package com.gs.lshly.middleware.sms.impl;

import cn.javaer.aliyun.sms.SmsClient;
import cn.javaer.aliyun.sms.SmsTemplate;
import com.gs.lshly.middleware.sms.ISMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 阿里云短信服务类
 */
@Component
public class SMSServiceAliyunImpl implements ISMSService {

    @Autowired
    private SmsClient smsClient;

    @Value("${aliyun.sms.templates.pickup.templateCode}")
    private String templateCode;

    @Value("${aliyun.sms.signName}")
    private String signName;

    @Value("${aliyun.sms.templates.settlement.templateCode}")
    private String settlementTemplateCode;

    @Value("${aliyun.sms.templates.settlementFaild.templateCode}")
    private String settlementFaildTemplateCode;

    @Value("${aliyun.sms.templates.settlementInform.templateCode}")
    private String settlementInformTemplateCode;

    @Value("${aliyun.sms.templates.pickup.newTemplateCode}")
    private String pickupTemplateCode;

    @Override
    public String sendRegistrySMSCode(String phone) {
        return smsClient.sendVerificationCode("registry", phone)+"";
    }

    @Override
    public String sendLoginSMSCode(String phone) {
        return smsClient.sendVerificationCode("login", phone)+"";
    }

    @Override
    public String sendPickUpSMSCode(String phone, String pickUpNum ,String name) {
        checkPhoneNumber(phone);
        Map<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("code", String.valueOf(pickUpNum));
        stringStringHashMap.put("name", String.valueOf(name));
        sendSMS(phone,stringStringHashMap,signName,templateCode);
        return pickUpNum;
    }

    @Override
    public String sendPickUpSMSCode(String phone, String pickUpNum, String name, String shopName) {
        checkPhoneNumber(phone);
        Map<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("code", String.valueOf(pickUpNum));
        stringStringHashMap.put("name", String.valueOf(name));
        stringStringHashMap.put("shopName", String.valueOf(shopName));
        sendSMS(phone,stringStringHashMap,signName,pickupTemplateCode);
        return pickUpNum;
    }

    @Override
    public String sendSettlementSMSCode(String phone, String userName, String pwd) {
        checkPhoneNumber(phone);
        Map<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("username", String.valueOf(userName));
        stringStringHashMap.put("password", String.valueOf(pwd));
        sendSMS(phone,stringStringHashMap,signName,settlementTemplateCode);
        return userName;
    }

    @Override
    public String sendSettlementFaildSMSCode(String phone, String shopName,String siteName, String reason) {
        checkPhoneNumber(phone);
        Map<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("shop_name", String.valueOf(shopName));
        stringStringHashMap.put("site_name", String.valueOf(siteName));
        stringStringHashMap.put("reason", String.valueOf(reason));
        sendSMS(phone,stringStringHashMap,signName,settlementFaildTemplateCode);
        return shopName;
    }

    @Override
    public String sendSettlementInformSMSCode(String phone, String shopName) {
        checkPhoneNumber(phone);
        Map<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("shop_name", String.valueOf(shopName));
        sendSMS(phone,stringStringHashMap,signName,settlementInformTemplateCode);
        return shopName;
    }

    private void sendSMS(String phone,Map<String,String> stringStringHashMap, String signName, String settlementTemplateCode) {
        SmsTemplate smsTemplate = new SmsTemplate();
        smsTemplate.setTemplateParam(stringStringHashMap);
        smsTemplate.setPhoneNumbers(Collections.singletonList(phone));
        smsTemplate.setSignName(signName);
        smsTemplate.setTemplateCode(settlementTemplateCode);
        smsClient.send(smsTemplate);
    }

    /**
     * 校验手机号码（中国）.
     *
     * @param phoneNumber the phone number
     */
    static void checkPhoneNumber(final String phoneNumber) {
        if (null == phoneNumber || !phoneNumber.matches(PHONE_NUMBER_REGEX)) {
            throw new IllegalArgumentException("Invalid phone number");
        }
    }
    /**
     * 宽松校验即可.
     */
    private static final String PHONE_NUMBER_REGEX = "\\d{5,}";


}
