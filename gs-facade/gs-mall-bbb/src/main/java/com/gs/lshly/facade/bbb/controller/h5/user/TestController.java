//package com.gs.lshly.facade.bbb.controller.h5.user;
//
//import com.gs.lshly.common.response.ResponseData;
//import com.gs.lshly.middleware.sms.IContactSMSService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Map;
//
///**
// * @Author yushaoqi
// * @Date 2021/7/12
// */
//@RestController
//@RequestMapping("/test")
//@Api(tags = "短信测试")
//public class TestController {
//
//    @Autowired
//    private IContactSMSService iContactSMSService;
//
//    @ApiOperation("用户登陆")
//    @GetMapping(value = "/userLogin")
//    public ResponseData<Void> userLogin(String phone){
//        Map<String, String> stringStringMap = iContactSMSService.userLogin(phone);
//        return ResponseData.success(stringStringMap.toString());
//    }
//
//    @ApiOperation("商户登录")
//    @GetMapping(value = "/merchantLogin")
//    public ResponseData<Void> merchantLogin(String phone){
//        Map<String, String> stringStringMap = iContactSMSService.merchantLogin(phone);
//        return ResponseData.success(stringStringMap.toString());
//    }
//
//    @ApiOperation("支付验证码")
//    @GetMapping(value = "/payment")
//    public ResponseData<Void> payment(String phone){
//        Map<String, String> stringStringMap = iContactSMSService.payment(phone);
//        return ResponseData.success(stringStringMap.toString());
//    }
//
//    @ApiOperation("下单成功提醒")
//    @GetMapping(value = "/reminderSuccessfulOrder")
//    public ResponseData<Void> reminderSuccessfulOrder(String phone,String tradeCode,String point,String balancePoint){
//        Map<String, String> stringStringMap = iContactSMSService.reminderSuccessfulOrder(phone,tradeCode,point,balancePoint);
//        return ResponseData.success(stringStringMap.toString());
//    }
//
//    @ApiOperation("已发货提醒")
//    @GetMapping(value = "/shipmentReminder")
//    public ResponseData<Void> shipmentReminder(String phone,String userName, String deliveryCompany,String deliveryCode){
//        Map<String, String> stringStringMap = iContactSMSService.shipmentReminder(phone,userName,deliveryCompany,deliveryCode);
//        return ResponseData.success(stringStringMap.toString());
//    }
//
//    @ApiOperation("已签收")
//    @GetMapping(value = "/signForSMS")
//    public ResponseData<Void> signForSMS(String phone,String userName, String deliveryCompany,String deliveryCode){
//        Map<String, String> stringStringMap = iContactSMSService.signForSMS(phone,userName,deliveryCompany,deliveryCode);
//        return ResponseData.success(stringStringMap.toString());
//    }
//
//    @ApiOperation("售后服务提醒")
//    @GetMapping(value = "/afterSalesServiceReminder")
//    public ResponseData<Void> afterSalesServiceReminder(String phone,String userName){
//        Map<String, String> stringStringMap = iContactSMSService.afterSalesServiceReminder(phone,userName);
//        return ResponseData.success(stringStringMap.toString());
//    }
//
//    @ApiOperation("退款提醒")
//    @GetMapping(value = "/refundReminder")
//    public ResponseData<Void> refundReminder(String phone,String userName){
//        Map<String, String> stringStringMap = iContactSMSService.refundReminder(phone,userName);
//        return ResponseData.success(stringStringMap.toString());
//    }
//
//    @ApiOperation("优惠券发放提醒")
//    @GetMapping(value = "/couponIssuanceReminder")
//    public ResponseData<Void> couponIssuanceReminder(String phone,String endDate,String loginUrl){
//        Map<String, String> stringStringMap = iContactSMSService.couponIssuanceReminder(phone,endDate,loginUrl);
//        return ResponseData.success(stringStringMap.toString());
//    }
//}
