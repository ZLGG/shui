package com.gs.lshly.middleware.mq.aliyun;

import com.aliyun.mq.http.MQClient;
import com.aliyun.mq.http.MQProducer;
import com.aliyun.mq.http.model.TopicMessage;

import java.util.Date;

public class HttpProducerTest {

    public static void main(String[] args) {
        MQClient mqClient = new MQClient(
                // 设置HTTP接入域名（此处以公共云生产环境为例）
                "http://1145295983493139.mqrest.cn-hangzhou.aliyuncs.com",
                // AccessKey 阿里云身份验证，在阿里云服务器管理控制台创建
                "LTAI4GEteCfR7fQp58F7wEdj",
                // SecretKey 阿里云身份验证，在阿里云服务器管理控制台创建
                "dncfGCWSJBHJsD4rR0O6D5dLnWLMrb"
        );

        // 所属的 Topic
        final String topic = "wait-pay-order";
        // Topic所属实例ID，默认实例为空
        final String instanceId = "MQ_INST_1145295983493139_BXQQmeVp";

        // 获取Topic的生产者
        MQProducer producer;
        if (instanceId != null && instanceId != "") {
            producer = mqClient.getProducer(instanceId, topic);
        } else {
            producer = mqClient.getProducer(topic);
        }

        try {
            // 循环发送100条消息
                TopicMessage pubMsg = new TopicMessage(
                        // 消息内容
                        "hello mq!".getBytes(),
                        // 消息标签
                        "A"
                );
                pubMsg.getProperties();
                pubMsg.setStartDeliverTime(System.currentTimeMillis()+10*1000);
                // 同步发送消息，只要不抛异常就是成功
                TopicMessage pubResultMsg = producer.publishMessage(pubMsg);

                // 同步发送消息，只要不抛异常就是成功
                System.out.println(new Date() + " Send mq message data. Topic is:" + topic + ", msgId is: " + pubResultMsg.getMessageId()
                        + ", bodyMD5 is: " + pubResultMsg.getMessageBodyMD5());

        } catch (Throwable e) {
            // 消息发送失败，需要进行重试处理，可重新发送这条消息或持久化这条数据进行补偿处理
            System.out.println(new Date() + " Send mq message failed. Topic is:" + topic);
            e.printStackTrace();
        }

        mqClient.close();
    }

}
