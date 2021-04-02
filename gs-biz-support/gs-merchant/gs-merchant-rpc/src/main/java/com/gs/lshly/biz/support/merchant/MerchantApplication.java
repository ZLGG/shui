package com.gs.lshly.biz.support.merchant;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * MerchantApplication
 *
 * @author lxus
 * @since 2020/9/14
 */
@SpringBootApplication
@EnableDubboConfig
@DubboComponentScan("com.gs.lshly.biz.support.merchant.rpc")
@MapperScan(basePackages="com.gs.lshly.biz.support.merchant.mapper")
@ComponentScan( value = {"com.gs.lshly.middleware.sms","com.gs.lshly.biz.support.merchant","com.gs.lshly.middleware.mail","com.gs.lshly.middleware.redis","com.gs.lshly.middleware.oss"})
public class MerchantApplication {

    public static void main(String[] args) {
        SpringApplication.run(MerchantApplication.class, args);
    }
}
