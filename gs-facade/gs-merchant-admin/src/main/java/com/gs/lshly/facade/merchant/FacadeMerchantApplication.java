package com.gs.lshly.facade.merchant;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * POS同步
 *
 * @author lxus
 * @since 2020/9/14
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.gs"})
@EnableDubboConfig
@DubboComponentScan("com.gs.lshly.facade.merchant")
@EnableAsync
public class FacadeMerchantApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacadeMerchantApplication.class, args);
    }
}
