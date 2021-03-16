package com.gs.lshly.biz.support.foundation;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
public class FoundationApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoundationApplication.class, args);
    }
}
