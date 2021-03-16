package com.lakala.boss.api.callutils;

import com.lakala.boss.api.config.MerchantBaseEnum;
import com.lakala.boss.api.entity.request.EntOffLinePaymentRequest;
import com.lakala.boss.api.entity.request.OrderDetail;
import com.lakala.boss.api.entity.response.EntOffLinePaymentResponse;
import com.lakala.boss.api.utils.BossClient;
import com.lakala.boss.api.utils.UuidUtil;
import org.apache.commons.lang3.ObjectUtils;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: LXF
 * @version: V1.0.0
 * @Project: lkl_boss_sdk
 * @Package: com.lakala.boss.api.demo
 * @Description: 预下单接口调用示例
 * @date: 2019-10-11 下午5:09:53
 */
public class EntOffLinePaymentDemo {

    public static void main(String[] args) {
        EntOffLinePaymentDemo demo = new EntOffLinePaymentDemo();
        URL url = EntOffLinePaymentDemo.class.getClassLoader().getResource(MerchantBaseEnum.merchant_hly_CertPath.getValue());
        try{
            FileInputStream fileInputStream = new FileInputStream(new File(url.getFile()));
            System.out.println(fileInputStream);
        }catch (Exception e){
            e.printStackTrace();
        }

        BossClient client = new BossClient(url.getFile(), MerchantBaseEnum.merchant_hly_CertPass.getValue(), MerchantBaseEnum.serverUrl.getValue());
        EntOffLinePaymentResponse response = demo.doPreOrder(client);
        if (ObjectUtils.isEmpty(response)) {
            System.out.println("下单失败");
        }
    }

    public EntOffLinePaymentResponse doPreOrder(BossClient client) {
        String orderAmt = "1000";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderTime = sdf.format(new Date());
        String merchantId = MerchantBaseEnum.merchant_hly_Id.getValue();

        EntOffLinePaymentRequest request = new EntOffLinePaymentRequest();
        request.setCharset("00");
        request.setVersion("1.0");
        request.setSignType("RSA");
        request.setService("EntOffLinePayment");
        request.setOfflineNotifyUrl("http://10.177.84.91:8080/mercStandard/idx.jsp");
        request.setClientIP("10.177.84.94");
        request.setRequestId(UuidUtil.getUuid());
        request.setMerchantId(merchantId);
        request.setOrderId(UuidUtil.getUuid());
        request.setOrderTime(orderTime);
        request.setTotalAmount(orderAmt);
        request.setCurrency("CNY");
        request.setBackParam("保留数据1");
        request.setSplitType("1");
        request.setValidUnit("00");
        request.setValidNum("15");
        //WECHATAPP:微信APP支付ALIPAYAPP 支付宝app支付
//        request.setTradeType("WECHATAPP");

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderSeqNo("001");//订单序号 订单顺序号,只能上送数字
        orderDetail.setDetailOrderId(UuidUtil.getUuid());//子订单号 商户的子订单号，保证全局唯一，不能与订单号重复
        orderDetail.setOrderAmt(orderAmt);//子订单金额，以分为单位，等于子订单支付金额与子订单手续费金额和,如果是按比例分账，此信息域上送分账比例，如40.75%上送为4075，我方收到后做相应缩小
        orderDetail.setShareFee("0");//分账手续费，如果上送，将按照此手续费计算，商户平台不需要指定手续费时，默认为0
        orderDetail.setRcvMerchantId(merchantId);//收款方商户编号
        orderDetail.setRcvMerchantIdName("ddd");//收款方商户名称
        orderDetail.setShowUrl("http://www.test.com/test/callback.jsp");//商品展示的url
        orderDetail.setProductId("编号01");//所购买商品的编号，只能是数字与字母
        orderDetail.setProductName("测试商品01");//所购买商品的名称
        orderDetail.setProductDesc("商品描述01");//所购买商品的描述

        List<OrderDetail> list = new ArrayList<OrderDetail>();
        list.add(orderDetail);

        request.setOrderDetail(list);

        try {
            EntOffLinePaymentResponse response = client.execute(request);
            System.out.println("RequestJsonStr=" + request.toString());
            if (response.isSuccess()) {
                System.out.println("[doPreOrder] 接口调用成功，开始处理业务逻辑");
                System.out.println("token=" + response.getToken());
                return response;
            } else {
                System.out.println("[doPreOrder] 接口调用失败");
                System.out.println("ResponseJsonStr=" + response.toString());
                System.out.println("ReturnCode=" + response.getReturnCode());
                System.out.println("ReturnMessage=" + response.getReturnMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
