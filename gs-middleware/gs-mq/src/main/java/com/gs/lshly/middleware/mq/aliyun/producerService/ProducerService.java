package com.gs.lshly.middleware.mq.aliyun.producerService;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.SendResult;

public interface ProducerService {

    /**
     * 同步发送消息
     * @param msgTag 标签，可用于消息小分类标注
     * @param messageBody 消息body内容，生产者自定义内容
     * @param msgKey 消息key值，建议设置全局唯一，可不传，不影响消息投递
     * @return success:SendResult or error:null
     */
    SendResult sendMsg(String msgTag, byte[] messageBody, String msgKey);

    /**
     * 同步发送定时/延时消息
     * @param msgTag 标签，可用于消息小分类标注，对消息进行再归类
     * @param messageBody 消息body内容，生产者自定义内容，二进制形式的数据
     * @param msgKey 消息key值，建议设置全局唯一值，可不设置，不影响消息收发
     * @param delayTime 服务端发送消息时间，立即发送输入0或比更早的时间
     * @return success:SendResult or error:null
     */
    SendResult sendTimeMsg(String msgTag,byte[] messageBody,String msgKey,long delayTime);

    /**
     * 发送单向消息
     */
    void sendOneWayMsg(String msgTag,byte[] messageBody,String msgKey);

    /**
     * 普通消息发送发放
     * @param msg 消息
     * @param isOneWay 是否单向发送
     */
    SendResult send(Message msg, Boolean isOneWay);

    /**
     * 异步发送普通消息
     * @param msgTag
     * @param messageBody
     * @param msgKey
     */
    void sendAsyncMsg(String msgTag,byte[] messageBody,String msgKey);


    void sendHttpMessage(String message);
}
