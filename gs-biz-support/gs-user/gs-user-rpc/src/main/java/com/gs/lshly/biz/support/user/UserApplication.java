package com.gs.lshly.biz.support.user;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * UserApplication
 *
 * @author lxus
 * @since 2020/9/14
 */
@SpringBootApplication
@EnableDubboConfig
@DubboComponentScan("com.gs.lshly.biz.support.user.rpc")
@MapperScan(basePackages="com.gs.lshly.biz.support.user.mapper")
@ComponentScan( value = {"com.gs.lshly.middleware.sms",
		"com.gs.lshly.middleware.mail", 
		"com.gs.lshly.middleware.redis", 
		"com.gs.lshly.middleware.crypt", 
		"com.gs.lshly.biz.support.user"})
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
