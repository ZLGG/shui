package com.gs.lshly.biz.support.trade.service.Comsumer.Impl;

import com.aliyun.mq.http.MQClient;
import com.aliyun.mq.http.MQConsumer;
import com.aliyun.mq.http.common.AckMessageException;
import com.aliyun.mq.http.model.Message;
import com.gs.lshly.middleware.mq.aliyun.ConSumerService.TradeCancelComsumerService;
import com.gs.lshly.middleware.mq.aliyun.config.MqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TradeCancelComsumerStart implements InitializingBean {
    @Autowired
    TradeCancelComsumerService tradeCancelComsumerService;

    @Autowired
    private MqConfig mqConfig;
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始bean：111111111111111111111111111111111111");
        MQClient mqClient = new MQClient(
                // 设置HTTP接入域名（此处以公共云生产环境为例）
                mqConfig.getNameSrvAddr(),
                // AccessKey 阿里云身份验证，在阿里云服务器管理控制台创建
                mqConfig.getAccessKey(),
                // SecretKey 阿里云身份验证，在阿里云服务器管理控制台创建
                mqConfig.getSecretKey()
        );

        // 所属的 Topic
        final String topic = mqConfig.getTimeTopic();
        // 您在控制台创建的 Group ID
        final String groupId = mqConfig.getTimeGroupId();
        // Topic所属实例ID，默认实例为空
        final String instanceId = mqConfig.getInstanceId();

        final MQConsumer consumer;
        if (instanceId != null && instanceId != "") {
            consumer = mqClient.getConsumer(instanceId, topic, groupId, null);
        } else {
            consumer = mqClient.getConsumer(topic, groupId);
        }

        // 在当前线程循环消费消息，建议是多开个几个线程并发消费消息
        new Thread() {
            @Override
            public void run() {
                do {
                    //System.out.println("消费者监听中~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    List<Message> messages = null;

                    try {
                        // 长轮询消费消息
                        // 长轮询表示如果topic没有消息则请求会在服务端挂住3s，3s内如果有消息可以消费则立即返回
                        messages = consumer.consumeMessage(
                                3,// 一次最多消费3条(最多可设置为16条)
                                3// 长轮询时间3秒（最多可设置为30秒）
                        );
                    } catch (Throwable e) {
                        e.printStackTrace();
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                    // 没有消息
                    if (messages == null || messages.isEmpty()) {
                        System.out.println(Thread.currentThread().getName() + ": no new message, continue!");
                        continue;
                    }

                    // 处理业务逻辑
                    for (Message message : messages) {
                        System.out.println("Receive message: " + message);
                        try {
                            tradeCancelComsumerService.TradeTimeOutCancel(message.getMessageBodyString());
                        }catch (Exception e) {
                            log.info("消费失败：~~~~~~————————{}",e.getMessage());
                        }
                    }

                    // Message.nextConsumeTime前若不确认消息消费成功，则消息会重复消费
                    // 消息句柄有时间戳，同一条消息每次消费拿到的都不一样
                    {
                        List<String> handles = new ArrayList<String>();
                        for (Message message : messages) {
                            handles.add(message.getReceiptHandle());
                        }

                        try {
                            consumer.ackMessage(handles);
                        } catch (Throwable e) {
                            // 某些消息的句柄可能超时了会导致确认不成功
                            if (e instanceof AckMessageException) {
                                AckMessageException errors = (AckMessageException) e;
                                System.out.println("Ack message fail, requestId is:" + errors.getRequestId() + ", fail handles:");
                                if (errors.getErrorMessages() != null) {
                                    for (String errorHandle :errors.getErrorMessages().keySet()) {
                                        System.out.println("Handle:" + errorHandle + ", ErrorCode:" + errors.getErrorMessages().get(errorHandle).getErrorCode()
                                                + ", ErrorMsg:" + errors.getErrorMessages().get(errorHandle).getErrorMessage());
                                    }
                                }
                                continue;
                            }
                            e.printStackTrace();
                        }
                    }
                } while (true);
            }
        }.start();


    }
}
