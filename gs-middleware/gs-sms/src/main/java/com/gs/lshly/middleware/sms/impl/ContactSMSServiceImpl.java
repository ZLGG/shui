package com.gs.lshly.middleware.sms.impl;

import com.gs.lshly.middleware.redis.RedisUtil;
import com.gs.lshly.middleware.sms.IContactSMSService;
import com.ztesoft.uccp.dubbo.interfaces.UCCPSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Author yushaoqi
 * @Date 2021/7/9
 */
@Component
public class ContactSMSServiceImpl implements IContactSMSService {
    // 用户登录和用户支付redis前缀
    private static final String PhoneValidCodeGroup = "PhoneValidCode_";
    // 商户登录验证码redis前缀
    private static final String PlatformPhoneCodeGroup = "PlatformPhone_User_";

    // UCCP分配的系统编码
    private static final String systemCode  = "DXJFSC";
    // UCCP分配的账号
    private static final String userAcct  = "DXJFSC";
    // UCCP分配的账号密码
    private static final String password  = "210712";

    // 登录短信校验
    private static final String userLoginSceneId = "7690";
    // 支付校验
    private static final String paymentVerificationSceneId = "7691";
    // 下单成功
    private static final String successfullyOrderedSceneId = "7692";
    // 发货成功
    private static final String shipSceneId = "7693";
    // 签收
    private static final String signForSceneId = "7694";
    // 换货退换货
    private static final String exchangeSceneId = "7695";
    // 退款
    private static final String refundSceneId = "7696";
    // 发优惠券
    private static final String couponSceneId = "7697";

    //号码归档地
    private static final String localNumber = "571";


    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Map<String, String> userLogin(String phone) {
        String key = PhoneValidCodeGroup + phone;
        String value = CyclicSequence(6);
        redisUtil.set(key, value, 5 * 60);

        HashMap<String, String> params = new HashMap<>();
        //接收消息推送的手机号码
        params.put("AccNbr", phone);
        //场景标识
        params.put("SceneId", userLoginSceneId);
        //如果使用场景模板来发送短信,必须填值 多个值用，连接
        params.put("ContentParam", value);
        HashMap<String, String> stringStringHashMap = sendShortMessage(params);
        return stringStringHashMap;
    }

    @Override
    public Map<String, String> merchantLogin(String phone) {

        String key = PlatformPhoneCodeGroup + phone;
        String value = CyclicSequence(6);
        redisUtil.set(key, value, 5 * 60);

        HashMap<String, String> params = new HashMap<>();
        //接收消息推送的手机号码
        params.put("AccNbr", phone);
        //场景标识
        params.put("SceneId", userLoginSceneId);
        //如果使用场景模板来发送短信,必须填值 多个值用，连接
        params.put("ContentParam", value);
        HashMap<String, String> stringStringHashMap = sendShortMessage(params);
        return stringStringHashMap;
    }

    @Override
    public Map<String, String> payment(String phone) {
        String key = PhoneValidCodeGroup + phone;
        String value = CyclicSequence(6);
        redisUtil.set(key, value, 5 * 60);

        HashMap<String, String> params = new HashMap<>();
        //接收消息推送的手机号码
        params.put("AccNbr", phone);
        //场景标识
        params.put("SceneId", paymentVerificationSceneId);
        //如果使用场景模板来发送短信,必须填值 多个值用，连接
        params.put("ContentParam", value);
        HashMap<String, String> stringStringHashMap = sendShortMessage(params);
        return stringStringHashMap;
    }

    @Override
    public Map<String, String> reminderSuccessfulOrder(String phone, String tradeCode, String point, String balancePoint) {
        // 判断是否含有，
        String tradeCode1 = StringReplacement(tradeCode);
        String point1 = StringReplacement(point);
        String balancePoint1 = StringReplacement(balancePoint);
        //拼装数据
        String contentParam = tradeCode1 + "," + point1 + "," + balancePoint1;

        HashMap<String, String> params = new HashMap<>();
        //接收消息推送的手机号码
        params.put("AccNbr", phone);
        //场景标识
        params.put("SceneId", successfullyOrderedSceneId);
        //如果使用场景模板来发送短信,必须填值 多个值用，连接
        params.put("ContentParam", contentParam);
        HashMap<String, String> stringStringHashMap = sendShortMessage(params);
        return stringStringHashMap;
    }

    @Override
    public Map<String, String> shipmentReminder(String phone, String userName, String deliveryCompany, String deliveryCode) {
        // 判断是否含有 ，
        String userName1 = StringReplacement(userName);
        String deliveryCompany1 = StringReplacement(deliveryCompany);
        String deliveryCode1 = StringReplacement(deliveryCode);
        //拼装数据
        String contentParam = userName1 + "," + deliveryCompany1 + "," + deliveryCode1;

        HashMap<String, String> params = new HashMap<>();
        //接收消息推送的手机号码
        params.put("AccNbr", phone);
        //场景标识
        params.put("SceneId", shipSceneId);
        //如果使用场景模板来发送短信,必须填值 多个值用，连接
        params.put("ContentParam", contentParam);
        HashMap<String, String> stringStringHashMap = sendShortMessage(params);
        return stringStringHashMap;
    }

    @Override
    public Map<String, String> signForSMS(String phone, String userName, String deliveryCompany, String deliveryCode) {
        // 判断是否含有 ，
        String userName1 = StringReplacement(userName);
        String deliveryCompany1 = StringReplacement(deliveryCompany);
        String deliveryCode1 = StringReplacement(deliveryCode);
        //拼装数据
        String contentParam = userName1 + "," + deliveryCompany1 + "," + deliveryCode1;

        HashMap<String, String> params = new HashMap<>();
        //接收消息推送的手机号码
        params.put("AccNbr", phone);
        //场景标识
        params.put("SceneId", signForSceneId);
        //如果使用场景模板来发送短信,必须填值 多个值用，连接
        params.put("ContentParam", contentParam);
        HashMap<String, String> stringStringHashMap = sendShortMessage(params);
        return stringStringHashMap;
    }

    @Override
    public Map<String, String> afterSalesServiceReminder(String phone, String userName) {
        // 判断是否含有 ，
        String userName1 = StringReplacement(userName);

        HashMap<String, String> params = new HashMap<>();
        //接收消息推送的手机号码
        params.put("AccNbr", phone);
        //场景标识
        params.put("SceneId", exchangeSceneId);
        //如果使用场景模板来发送短信,必须填值 多个值用，连接
        params.put("ContentParam", userName1);
        HashMap<String, String> stringStringHashMap = sendShortMessage(params);
        return stringStringHashMap;
    }

    @Override
    public Map<String, String> refundReminder(String phone, String userName) {
        // 判断是否含有 ，
        String userName1 = StringReplacement(userName);

        HashMap<String, String> params = new HashMap<>();
        //接收消息推送的手机号码
        params.put("AccNbr", phone);
        //场景标识
        params.put("SceneId", refundSceneId);
        //如果使用场景模板来发送短信,必须填值 多个值用，连接
        params.put("ContentParam", userName1);
        HashMap<String, String> stringStringHashMap = sendShortMessage(params);
        return stringStringHashMap;
    }

    @Override
    public Map<String, String> couponIssuanceReminder(String phone, String endDate, String loginUrl) {
        // 判断是否含有 ，
        String endDate1 = StringReplacement(endDate);
        String loginUrl1 = StringReplacement(loginUrl);
        //拼装数据
        String contentParam = endDate1 + "," + loginUrl1;

        HashMap<String, String> params = new HashMap<>();
        //接收消息推送的手机号码
        params.put("AccNbr", phone);
        //场景标识
        params.put("SceneId", couponSceneId);
        //如果使用场景模板来发送短信,必须填值 多个值用，连接
        params.put("ContentParam", contentParam);
        HashMap<String, String> stringStringHashMap = sendShortMessage(params);
        return stringStringHashMap;
    }

    /**
     * 功能描述  调用统一接触信息推送平台发送短信
     *
     * @param params
     * @return java.util.HashMap<java.lang.String, java.lang.String>
     * @author yushaoqi
     * @date 2021/7/9
     */
    public HashMap<String, String> sendShortMessage(HashMap<String, String> params) {
        SimpleDateFormat transactionsdf = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat requestTimesdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String transactionFormat = transactionsdf.format(new Date());
        String transactionId = systemCode + transactionFormat + CyclicSequence(10);
        String requestTime = requestTimesdf.format(new Date());
        //请求消息流水，格式：系统编码（6位）+yyyymmddhhmiss+10位序列号
        params.put("TransactionId", transactionId);
        //UCCP分配的系统编码
        params.put("SystemCode", systemCode);
        //UCCP分配的认证密码
        params.put("Password", userAcct);
        //UCCP分配的帐号
        params.put("UserAcct", password);
        //请求的时间,请求发起的时间,必须为下边的格式
        params.put("RequestTime", requestTime);
        //消息内容
        params.put("OrderContent", "");
        //本地网/辖区
        params.put("LanId", localNumber);
        //定时发送的时间设置
        params.put("SendDate", "");
        //外系统流水ID,查询发送结构用,可填
        params.put("ExtOrderId", "");

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/dubbo-consumer.xml");
        context.start();
        //通过spring获取实例
        UCCPSendService service = (UCCPSendService) context.getBean("UCCPSendServiceImpl");
        HashMap<String, String> reqMap = new HashMap<>();
        try {
            reqMap = (HashMap<String, String>) service.sendShortMessage(params);
        } catch (Exception e) {
            reqMap.put("msg","请求出错");
        }
        return reqMap;
    }

    /**
     * 功能描述  生成随机数
     *
     * @return java.lang.String
     * @author yushaoqi
     * @date 2021/7/9
     */
    public String CyclicSequence(int Number) {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < Number; i++) {
            result += random.nextInt(10);
        }
        System.out.println(result);
        return result;
    }

    /**
     * 功能描述 将字符串中的，替换
     *
     * @param s
     * @return java.lang.String
     * @author yushaoqi
     * @date 2021/7/9
     */
    public String StringReplacement(String s) {
        String replace = s.replace(",", "&comma;");
        return replace;
    }
}
