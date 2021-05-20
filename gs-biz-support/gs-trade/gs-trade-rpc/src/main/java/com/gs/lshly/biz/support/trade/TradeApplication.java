package com.gs.lshly.biz.support.trade;

import com.gs.lshly.biz.support.trade.service.test.MQConsumerTest;
import com.gs.lshly.biz.support.trade.service.test.MQProducerTest;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.apache.rocketmq.spring.autoconfigure.RocketMQAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * StockApplication
 *
 * @author lxus
 * @since 2020/9/14
 */
//启用rocketmq,注释掉exclude
@SpringBootApplication(
        exclude = RocketMQAutoConfiguration.class
)
@EnableDubboConfig
@DubboComponentScan("com.gs.lshly.biz.support.trade.rpc")
@MapperScan(basePackages="com.gs.lshly.biz.support.trade.mapper")
@ComponentScan( value = {"com.gs.lshly.middleware.sms",
        "com.gs.lshly.biz.support.trade",
        "com.gs.lshly.middleware.mq",
        "com.gs.lshly.middleware.redis"}
    //启用rocketmq,注释掉excludeor
    ,excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {MQConsumerTest.class, MQProducerTest.class})}
    )
@EnableTransactionManagement
public class TradeApplication {
    public static void main(String[] args) {
        SpringApplication.run(TradeApplication.class, args);
    }

}
