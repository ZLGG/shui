package com.gs.lshly.biz.support.trade.rpc.test;

import com.gs.lshly.biz.support.trade.service.test.MQConsumerTest;
import com.gs.lshly.biz.support.trade.service.test.MQProducerTest;
import com.gs.lshly.rpc.api.test.IMQTestRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class MQTestRpc implements IMQTestRpc {

    @Autowired(required = false)
    MQProducerTest producerTest;

    @Autowired(required = false)
    MQConsumerTest consumerTest;

    @Override
    public String sendOrder() {
        return producerTest.sendOrder();
    }

    @Override
    public String queryOrderById(String orderId) {
        return consumerTest.queryOrderById(orderId);
    }
}
