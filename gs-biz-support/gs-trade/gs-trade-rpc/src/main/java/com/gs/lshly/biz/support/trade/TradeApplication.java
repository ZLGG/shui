package com.gs.lshly.biz.support.trade;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * StockApplication
 *
 * @author lxus
 * @since 2020/9/14
 */
@SpringBootApplication
@EnableDubboConfig
@DubboComponentScan("com.gs.lshly.biz.support.trade.rpc")
@MapperScan(basePackages="com.gs.lshly.biz.support.trade.mapper")
@ComponentScan( value = {"com.gs.lshly.middleware.sms","com.gs.lshly.biz.support.trade"})
@EnableTransactionManagement
public class TradeApplication {
    public static void main(String[] args) {
        SpringApplication.run(TradeApplication.class, args);
    }
}
