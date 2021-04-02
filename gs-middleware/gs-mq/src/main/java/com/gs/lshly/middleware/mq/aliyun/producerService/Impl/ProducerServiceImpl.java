package com.gs.lshly.middleware.mq.aliyun.producerService.Impl;

import com.aliyun.mq.http.MQClient;
import com.aliyun.mq.http.MQProducer;
import com.aliyun.mq.http.model.TopicMessage;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.OnExceptionContext;
import com.aliyun.openservices.ons.api.SendCallback;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.aliyun.openservices.ons.api.exception.ONSClientException;
import com.gs.lshly.middleware.mq.aliyun.config.MqConfig;
import com.gs.lshly.middleware.mq.aliyun.producerService.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    private MqConfig config;

    @Autowired
    private ProducerBean producer;


    /**
     * 同步发送消息
     * @param msgTag 标签，可用于消息小分类标注
     * @param messageBody 消息body内容，生产者自定义内容
     * @param msgKey 消息key值，建议设置全局唯一，可不传，不影响消息投递
     * @return success:SendResult or error:null
     */
    @Override
    public SendResult sendMsg(String msgTag, byte[] messageBody, String msgKey) {
        Message msg = new Message(config.getTopic(),msgTag,msgKey,messageBody);
        return this.send(msg,Boolean.FALSE);
    }


    /**
     * 同步发送定时/延时消息
     * @param msgTag 标签，可用于消息小分类标注，对消息进行再归类
     * @param messageBody 消息body内容，生产者自定义内容，二进制形式的数据
     * @param msgKey 消息key值，建议设置全局唯一值，可不设置，不影响消息收发
     * @param delayTime 服务端发送消息时间，立即发送输入0或比更早的时间
     * @return success:SendResult or error:null
     */
    @Override
    public SendResult sendTimeMsg(String msgTag,byte[] messageBody,String msgKey,long delayTime) {
        Message msg = new Message(config.getTimeTopic(),msgTag,msgKey,messageBody);
        msg.setStartDeliverTime(delayTime);
        return this.send(msg,Boolean.FALSE);
    }


    /**
     * 发送单向消息
     */
    @Override
    public void sendOneWayMsg(String msgTag,byte[] messageBody,String msgKey) {
        Message msg = new Message(config.getTopic(),msgTag,msgKey,messageBody);
        this.send(msg,Boolean.TRUE);
    }


    /**
     * 普通消息发送发放
     * @param msg 消息
     * @param isOneWay 是否单向发送
     */
    @Override
    public SendResult send(Message msg,Boolean isOneWay) {
        try {
            if(isOneWay) {
                //由于在 oneway 方式发送消息时没有请求应答处理，一旦出现消息发送失败，则会因为没有重试而导致数据丢失。
                //若数据不可丢，建议选用同步或异步发送方式。
                producer.sendOneway(msg);
                success(msg, "单向消息MsgId不返回");
                return null;
            }else {
                //可靠同步发送
                SendResult sendResult = producer.send(msg);
                //获取发送结果，不抛异常即发送成功
                if (sendResult != null) {
                    success(msg, sendResult.getMessageId());
                    return sendResult;
                }else {
                    error(msg,null);
                    return null;
                }
            }
        } catch (Exception e) {
            System.out.println("__________:  "+e.getCause());
            error(msg,e);
            return null;
        }
    }

    //对于使用异步接口，可设置单独的回调处理线程池，拥有更灵活的配置和监控能力。
    //根据项目需要，服务器配置合理设置线程数，线程太多有OOM 风险，
    private ExecutorService threads = Executors.newFixedThreadPool(3);
    //仅建议执行轻量级的Callback任务，避免阻塞公共线程池 引起其它链路超时。



    /**
     * 异步发送普通消息
     * @param msgTag
     * @param messageBody
     * @param msgKey
     */
    @Override
    public void sendAsyncMsg(String msgTag,byte[] messageBody,String msgKey) {
        producer.setCallbackExecutor(threads);

        Message msg = new Message(config.getTopic(),msgTag,msgKey,messageBody);
        try {
            producer.sendAsync(msg, new SendCallback() {
                @Override
                public void onSuccess(final SendResult sendResult) {
                    assert sendResult != null;
                    success(msg, sendResult.getMessageId());
                }
                @Override
                public void onException(final OnExceptionContext context) {
                    //出现异常意味着发送失败，为了避免消息丢失，建议缓存该消息然后进行重试。
                    error(msg,context.getException());
                }
            });
        } catch (ONSClientException e) {
            error(msg,e);
        }
    }

    @Override
    public void sendHttpMessage(String message) {
        MQClient mqClient = new MQClient(
                // 设置HTTP接入域名（此处以公共云生产环境为例）
                config.getNameSrvAddr(),
                // AccessKey 阿里云身份验证，在阿里云服务器管理控制台创建
                config.getAccessKey(),
                // SecretKey 阿里云身份验证，在阿里云服务器管理控制台创建
                config.getSecretKey()
        );

        // 所属的 Topic
        final String topic = config.getTimeTopic();
        // Topic所属实例ID，默认实例为空
        final String instanceId = config.getInstanceId();

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
                    message.getBytes(),
                    // 消息标签
                    "A"
            );
            pubMsg.getProperties();
            pubMsg.setStartDeliverTime(System.currentTimeMillis()+Integer.parseInt(config.getTime())*1000);
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


    //--------------日志打印----------
    private void error(Message msg,Exception e) {
        log.error("发送MQ消息失败-- Topic:{}, Key:{}, tag:{}, body:{}"
                ,msg.getTopic(),msg.getKey(),msg.getTag(),new String(msg.getBody()));
        log.error("errorMsg --- {},eCause -- {}",e.getMessage(),e.getCause());
    }
    private void success(Message msg,String messageId) {
        log.info("发送MQ消息成功 -- Topic:{} ,msgId:{} , Key:{}, tag:{}, body:{}"
                ,msg.getTopic(),messageId,msg.getKey(),msg.getTag(),new String(msg.getBody()));
    }
}
