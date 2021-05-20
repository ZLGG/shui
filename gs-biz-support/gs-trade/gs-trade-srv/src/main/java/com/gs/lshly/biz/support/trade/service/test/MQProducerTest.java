package com.gs.lshly.biz.support.trade.service.test;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.gs.lshly.common.struct.test.TradeTestMQDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class MQProducerTest implements SendCallback{

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Value("${rocketmq.topic.trade.test}")
    private String tradeTestTopic;


    public String sendOrder() {
        TradeTestMQDTO dto = new TradeTestMQDTO().setOrderId(StrUtil.uuid()).setValue("测试:" + RandomUtil.randomNumbers(4));

        //异步发送
        rocketMQTemplate.asyncSend(tradeTestTopic, dto, this);

        return dto.getOrderId();
    }

    @Override
    public void onSuccess(SendResult sendResult) {
        log.info("发送成功");
    }

    @Override
    public void onException(Throwable throwable) {
        log.error("发送失败:"+throwable.getMessage(), throwable);
    }
}
