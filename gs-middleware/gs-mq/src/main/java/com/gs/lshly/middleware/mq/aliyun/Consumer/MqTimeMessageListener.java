package com.gs.lshly.middleware.mq.aliyun.Consumer;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.gs.lshly.middleware.mq.aliyun.ConSumerService.TradeCancelComsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MqTimeMessageListener implements MessageListener {
    @Autowired
    private TradeCancelComsumerService tradeCancelComsumerService;
    @Override
    public Action consume(Message message, ConsumeContext consumeContext) {
        log.info("接收到MQ消息 -- Topic:{}, tag:{},msgId:{} , Key:{}, body:{}",
                message.getTopic(),message.getTag(),message.getMsgID(),message.getKey(),new String(message.getBody()));

        try {

            String msgTag = message.getTag();//消息类型
            String msgKey = message.getKey();//业务唯一id
            switch (msgTag) {
                //----通过生产者传的tag标签进行消息分类和过滤处理
                case "TradeTimeOutCancel":
                    //通过唯一key的，比如前面key传的值是订单号或者用户id这种唯一值，来进行数据的查询或处理
                    //由于RocketMQ能重复推送消息，处理消息的时候做好数据的幂等，防止重复处理
                    if(!msgKey.equals("5e10ac22eb5348dc928e425c1fbf2841")) {//如订单系统需要判断订单是否被处理过等，通过传的msgKey即订单号去查询数据库进行判断
                        break;
                    }

                    tradeCancelComsumerService.TradeTimeOutCancel(new String(message.getBody()));
            //验证通过，处理业务
            //do something
            break;
        }
        //消费成功，继续消费下一条消息
        return Action.CommitMessage;
        } catch (Exception e) {
        log.error("消费MQ消息失败！ msgId:" + message.getMsgID()+"----ExceptionMsg:"+e.getMessage());
        //消费失败，告知服务器稍后再投递这条消息，继续消费其他消息
        return Action.ReconsumeLater;
    }
    }
}
