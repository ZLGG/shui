package com.gs.lshly.biz.support.ctcc;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年4月6日 上午10:06:15
 */
@SpringBootApplication
@EnableDubboConfig
@DubboComponentScan("com.gs.lshly.biz.support.ctcc.rpc")
public class CtccApplication {

    public static void main(String[] args) {
        SpringApplication.run(CtccApplication.class, args);
    }
}
