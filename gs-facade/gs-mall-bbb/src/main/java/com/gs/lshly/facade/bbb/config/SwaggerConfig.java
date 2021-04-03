package com.gs.lshly.facade.bbb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gs.lshly.common.utils.DocketUtils;

import springfox.documentation.spring.web.plugins.Docket;

/**
 * SwaggerConfig
 *
 * @author lxus
 * @since 2020/9/14
 */
//@EnableSwaggerBootstrapUI
@Configuration
@Profile(value = {"dev","test","hlytest","hztest"})
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket create2BPCApi() {
        return DocketUtils.create2BPCApi("电信商城");
    }
    @Bean
    public Docket create2BH5Api() {
        return DocketUtils.create2BH5Api("电信商城");
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
