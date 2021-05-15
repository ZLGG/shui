package com.gs.lshly.biz.support.foundation;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * PlatformApplication
 *
 * @author lxus
 * @since 2020/9/14
 */
@SpringBootApplication
@EnableDubboConfig
@DubboComponentScan("com.gs.lshly.biz.support.foundation.rpc")
@MapperScan(basePackages="com.gs.lshly.biz.support.foundation.mapper")
@ComponentScan(value = {"com.gs.lshly.middleware.sms",
		"com.gs.lshly.middleware.redis",
		"com.gs.lshly.biz.support.foundation"})
public class FoundationApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoundationApplication.class, args);
    }
}
