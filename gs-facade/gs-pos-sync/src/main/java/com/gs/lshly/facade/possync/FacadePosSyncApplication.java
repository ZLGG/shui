package com.gs.lshly.facade.possync;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * POS同步
 *
 * @author lxus
 * @since 2020/9/14
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.gs"})
@EnableDubboConfig
@DubboComponentScan("com.gs.lshly.facade.possync")
@EnableAsync
public class FacadePosSyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacadePosSyncApplication.class, args);
    }
}
