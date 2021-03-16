package com.gs.lshly.biz.support.commodity;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * CommodityApplication
 *
 * @author lxus
 * @since 2020/9/14
 */
@SpringBootApplication
@EnableDubboConfig
@DubboComponentScan("com.gs.lshly.biz.support.commodity.rpc")
@MapperScan(basePackages="com.gs.lshly.biz.support.commodity.mapper")
@ComponentScan( value = {"com.gs.lshly.middleware.redis","com.gs.lshly.biz.support.commodity","com.gs.lshly.middleware.oss"})
public class CommodityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommodityApplication.class, args);
    }
}
