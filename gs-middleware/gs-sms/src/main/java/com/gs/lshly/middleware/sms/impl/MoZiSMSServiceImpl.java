package com.gs.lshly.middleware.sms.impl;

import org.apache.http.HttpResponse;
import cn.javaer.aliyun.sms.SmsClient;
import com.alibaba.fastjson.JSON;
import com.google.common.hash.Hashing;
import com.gs.lshly.common.utils.HttpsUtil;
import com.gs.lshly.middleware.sms.IMoZiSMSService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 摩字短信服务类
 */
@Component
@Slf4j
public class MoZiSMSServiceImpl implements IMoZiSMSService {

        @Autowired
        private SmsClient smsClient;


        public static class SmsURL {
            //短信内容发送
            private static String contentUrl =  "https://api.mozisms.com/sms/send/content";
            //短信模板发送
            private static String templateUrl = "https://api.mozisms.com/sms/send/template";
            //余额查询接口
            private static String statusUrl = "https://api.mozisms.com/sms/status/batchQuery/v2";
        }

        public static class Merchant {
            //卫盈智信-营销
            //商户号
            private static String merchantId = "1002fc";
            //对应 apikey
            private static String merchantApiKey = "RiK4Di";

            //卫盈智信-通知
            //商户号
            private static String merchantId2= "1002fb";
            //对应 apikey
            private static String merchantApiKey2 = "zNs8i6";
        }


        /**
          * 内容发送接口
          * @param mobiles 手机号
          * @param sign 已审核短信签名
          * @param content 内容
          * @param merchantOrderId 商户订单号
          * @return
         */
        @Override
        public  String sendTemplate(String mobiles, String sign, String content, String merchantOrderId) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("merchantOrderId", merchantOrderId);
            param.put("sign", sign);
            param.put("content", content);
            param.put("mobiles", mobiles);
            return postSms(SmsURL.contentUrl, param);
        }

        /**
          * 模板发送接口
          * @param mobiles 手机号
          * @param tplId 已审核模板id
          * @param parameter 模板参数，无参数传空 map{}
          * @param merchantOrderId 商户订单号
          * @return
         */
        @Override
        public  String TemplateDate(String mobiles, String tplId, Map<String,Object> parameter, String merchantOrderId) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("merchantOrderId", merchantOrderId);
            param.put("tplId", tplId);
            param.put("parameter", parameter);
            param.put("mobiles", mobiles);
            return postSms(SmsURL.templateUrl, param);
        }

        /**
         * 余额查询接口
         * @param merchantId 对应商户id
         * @return
         */
        @Override
        public String balanceQueryDate(String merchantId) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("merchantId", merchantId);
            return postSms(SmsURL.statusUrl, param);
        }


        /**
         * 发送短信
         *
         * @param url 发送 url
         * @param param data
         * @return
         */
        private static String postSms(String url, Map<String, Object> param){
            String data = JSON.toJSONString(param);
            String responseTxt = "";
            try {
                HttpPost request = new HttpPost(url.trim());
                setPostHead(request, buildSign(data, Merchant.merchantApiKey));
                request.setEntity(new StringEntity(data, HTTP.UTF_8));
                HttpResponse response = HttpsUtil.getClient().execute(request);
                Integer code = response.getStatusLine().getStatusCode();
                if (200 == code) {
                    responseTxt = EntityUtils.toString(response.getEntity(), "utf-8");
                } else {
                    responseTxt = EntityUtils.toString(response.getEntity(), "utf-8");
                }
            } catch (Exception e) {
                System.out.println("发送异常" + e.getMessage());
            }
            return responseTxt;
        }


        public static void setPostHead(HttpUriRequest request, String sign) {
            request.setHeader("Accept", "application/json;charset=utf-8;");
            request.setHeader("Content-Type", "application/json;charset=utf-8;");
            request.setHeader("merchantId", Merchant.merchantId);
            request.setHeader("sign", sign);
        }


        public static  String sendDate(String mobiles, String sign, String content, String merchantOrderId) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("merchantOrderId", merchantOrderId);
            param.put("sign", sign);
            param.put("content", content);
            param.put("mobiles", mobiles);
            return postSms(SmsURL.contentUrl, param);
        }


        public static String balanceQuery(String merchantId) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("merchantId", merchantId);
            return postSms(SmsURL.statusUrl, param);
        }


        /**
              * 构建请求头
              *
              * @param sign 动态签名
              * @return
         */
//        public static Headers getPostHead(String sign) {
//            return new Headers.Builder()
//                    .add("Accept", "application/json;charset=utf-8;")
//                    .add("Content-Type", "application/json;charset=utf-8;")
//                    .add("merchantId", Merchant.merchantId)
//                    .add("sign", sign)
//                    .build();
//        }


        /**
         * 生成请求签名
         *
         * @param data body 数据
         * @param apikey apikey
         * @return
         */
        private static String buildSign(String data, String apikey) throws
                UnsupportedEncodingException {
            return Hex.encodeHexString(Hashing.sha1().hashBytes((data +
                    apikey).getBytes("UTF-8")).asBytes());
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


        public static void main(String[] args) {
            String content = sendDate("13027710907", "摩字", "验证码是#code#，您在测试短信服务，如非本人操作，请忽略。", "a123456");
//            System.out.println(content);

            String balanceQuery = balanceQuery( "562715089242107961");
            System.out.println(balanceQuery);


        }
}
