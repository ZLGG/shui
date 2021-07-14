package com.gs.lshly.middleware.sms.impl;

import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.stereotype.Component;

import com.gs.lshly.middleware.sms.IContactSMSService;

/**
 * @Author yushaoqi
 * @Date 2021/7/9
 */
@Component
public class ContactSMSServiceImpl implements IContactSMSService {

    private static final String url = "http://127.0.0.1:8888/test";

    @Override
    public String userLogin(String phone) {
        String userLoginUrl = url + "/userLogin?phone=" + phone;
        String s = sendGet(userLoginUrl);
        return s;
    }

    @Override
    public String merchantLogin(String phone) {
        String merchantLoginUrl = url + "/merchantLogin?phone=" + phone;
        String s = sendGet(merchantLoginUrl);
        return s;
    }

    @Override
    public String payment(String phone) {
        String paymentUrl = url + "/payment?phone=" + phone;
        String s = sendGet(paymentUrl);
        return s;
    }

    @Override
    public String reminderSuccessfulOrder(String phone, String tradeCode, String point, String balancePoint) {
        String reminderSuccessfulOrderUrl = url + "/reminderSuccessfulOrder?phone=" + phone
                + "&tradeCode=" + tradeCode
                + "&point=" + point
                + "&balancePoint=" + balancePoint;
        String s = sendGet(reminderSuccessfulOrderUrl);
        return s;
    }

    @Override
    public String shipmentReminder(String phone, String userName, String deliveryCompany, String deliveryCode) {
        String shipmentReminderUrl = url + "/shipmentReminder?phone=" + phone
                + "&userName=" + userName
                + "&deliveryCompany=" + deliveryCompany
                + "&deliveryCode=" + deliveryCode;
        String s = sendGet(shipmentReminderUrl);
        return s;
    }

    @Override
    public String signForSMS(String phone, String userName, String deliveryCompany, String deliveryCode) {
        String signForSMSUrl = url + "/signForSMS?phone=" + phone
                + "&userName=" + userName
                + "&deliveryCompany=" + deliveryCompany
                + "&deliveryCode=" + deliveryCode;
        String s = sendGet(signForSMSUrl);
        return s;
    }

    @Override
    public String afterSalesServiceReminder(String phone, String userName) {
        String afterSalesServiceReminderUrl = url + "/afterSalesServiceReminder?phone=" + phone
                + "&userName=" + userName;
        String s = sendGet(afterSalesServiceReminderUrl);
        return s;
    }

    @Override
    public String refundReminder(String phone, String userName) {
        String refundReminderUrl = url + "/refundReminder?phone=" + phone
                + "&userName=" + userName;
        String s = sendGet(refundReminderUrl);
        return s;
    }

    @Override
    public String couponIssuanceReminder(String phone, String endDate, String loginUrl) {
        String couponIssuanceReminderUrl = url + "/couponIssuanceReminder?phone=" + phone
                + "&endDate=" + endDate
                + "&loginUrl=" + loginUrl;
        String s = sendGet(couponIssuanceReminderUrl);
        return s;
    }

    //    public static String sendPost(String urlParam) throws HttpException, IOException {
//        // 创建httpClient实例对象
//        HttpClient httpClient = new HttpClient();
//        // 设置httpClient连接主机服务器超时时间：15000毫秒
//        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
//        // 创建post请求方法实例对象
//        PostMethod postMethod = new PostMethod(urlParam);
//        // 设置post请求超时时间
//        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
//        postMethod.addRequestHeader("Content-Type", "application/json");
//
//        httpClient.executeMethod(postMethod);
//
//        String result = postMethod.getResponseBodyAsString();
//        postMethod.releaseConnection();
//        return result;
//    }
    public static String sendGet(String urlParam) {
        // 创建httpClient实例对象
        HttpClient httpClient = new HttpClient();
        // 设置httpClient连接主机服务器超时时间：15000毫秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        // 创建GET请求方法实例对象
        GetMethod getMethod = new GetMethod(urlParam);
        // 设置post请求超时时间
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
        getMethod.addRequestHeader("Content-Type", "application/json");
        String result = null;
        try {
            httpClient.executeMethod(getMethod);
            result = getMethod.getResponseBodyAsString();
        } catch (Exception e) {
            result = "发送请求失败";
        }
        getMethod.releaseConnection();
        return result;
    }

    public static void main(String[] args) {
        String couponIssuanceReminderUrl = url + "/couponIssuanceReminder?phone=" + 11111
                + "&endDate=" + 2222
                + "&loginUrl=" + 3333;
        String s = sendGet(couponIssuanceReminderUrl);
        System.out.println(s);
    }

}
