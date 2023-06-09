package com.gs.lshly.facade.bbc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gs.lshly.common.utils.DocketUtils;

import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig
 *
 * @author lxus
 * @since 2020/9/14
 */
@Configuration
@Profile(value = {"dev", "hztest","ctcctest"})
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket create2BPCApi() {
        return DocketUtils.create2CPCApi("PC端商城");
    }

    @Bean
    public Docket create2BH5Api() {
        return DocketUtils.create2CH5Api("H5端商城");
    }

    @Bean
    public Docket create2BFYApi() {
        return DocketUtils.create2CFYApi("测试");
    }
    
    @Bean
    public Docket createCtccApi() {
        return DocketUtils.createCtccApi("电信接口DEMO");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
