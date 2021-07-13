package com.gs.lshly.middleware.sms;

import java.util.Map;

public interface IContactSMSService  {
    /**
     *功能描述  用户登录
     * @author yushaoqi
     * @date 2021/7/9
     * @param phone
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    Map<String, String> userLogin(String phone);
    /**
     *功能描述  商户登录
     * @author yushaoqi
     * @date 2021/7/9
     * @param phone
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    Map<String, String> merchantLogin(String phone);
    /**
     *功能描述 支付验证码
     * @author yushaoqi
     * @date 2021/7/9
     * @param phone
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    Map<String, String> payment(String phone);
    /**
     *功能描述 下单成功提醒
     * @author yushaoqi
     * @date 2021/7/9
     * @param phone 手机号
     * @param tradeCode 订单号
     * @param point  消耗积分
     * @param balancePoint  积分余额
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    Map<String, String> reminderSuccessfulOrder(String phone,String tradeCode,String point,String balancePoint);

    /**
     *功能描述 已发货提醒
     * @author yushaoqi
     * @date 2021/7/9
     * @param phone 手机号
     * @param userName 用户名
     * @param deliveryCompany 物流公司名称
     * @param deliveryCode  快递单号
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    Map<String, String> shipmentReminder(String phone,String userName, String deliveryCompany,String deliveryCode);

    /**
     *功能描述 已签收短信
     * @author yushaoqi
     * @date 2021/7/9
     * @param phone 手机号
     * @param userName 用户名
     * @param deliveryCompany 物流公司名称
     * @param deliveryCode 快递单号
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    Map<String, String> signForSMS(String phone,String userName, String deliveryCompany,String deliveryCode);

    /**
     *功能描述  售后服务提醒
     * @author yushaoqi
     * @date 2021/7/9
     * @param phone 手机号
     * @param userName 用户名称
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    Map<String, String> afterSalesServiceReminder(String phone,String userName);
    /**
     *功能描述 退款提醒
     * @author yushaoqi
     * @date 2021/7/9
     * @param phone 手机号
     * @param userName 用户名称
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    Map<String, String> refundReminder(String phone,String userName);

    /**
     *功能描述  优惠券发放提醒
     * @author yushaoqi
     * @date 2021/7/9
     * @param phone 手机号
     * @param endDate 优惠券结束时间 YYYY-MM-dd HH:mm:ss
     * @param loginUrl  登录url
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    Map<String, String> couponIssuanceReminder(String phone,String endDate,String loginUrl);

}
