package com.gs.lshly.biz.support.trade.service.test;


import cn.hutool.json.JSONUtil;
import com.gs.lshly.common.struct.test.TradeTestMQDTO;
import com.gs.lshly.middleware.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RocketMQMessageListener(topic = "${rocketmq.topic.trade.test}", consumerGroup = "trade_test")
public class MQConsumerTest implements RocketMQListener<TradeTestMQDTO> {

    @Autowired
    RedisUtil redisUtil;

    public String queryOrderById(String orderId) {
        Object obj = redisUtil.get("trade_test_" + orderId);
        if (obj != null) {
            return JSONUtil.toJsonStr(obj);
        }
        return "未找到";
    }

    @Override
    public void onMessage(TradeTestMQDTO tradeTestMQDTO) {
        log.info("收到消息:" + JSONUtil.toJsonStr(tradeTestMQDTO));
        redisUtil.set("trade_test_" + tradeTestMQDTO.getOrderId(), tradeTestMQDTO, TimeUnit.MINUTES.toSeconds(10));
    }

}
