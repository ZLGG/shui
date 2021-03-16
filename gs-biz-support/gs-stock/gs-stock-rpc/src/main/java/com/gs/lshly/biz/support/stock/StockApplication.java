package com.gs.lshly.biz.support.stock;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * StockApplication
 *
 * @author lxus
 * @since 2020/9/14
 */
@SpringBootApplication
@EnableDubboConfig
@DubboComponentScan("com.gs.lshly.biz.support.stock.rpc")
@MapperScan(basePackages="com.gs.lshly.biz.support.stock.mapper")
public class StockApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockApplication.class, args);
    }
}
