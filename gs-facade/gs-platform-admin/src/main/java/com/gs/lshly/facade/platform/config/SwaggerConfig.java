package com.gs.lshly.facade.platform.config;

import org.springframework.beans.factory.annotation.Value;
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
@EnableSwagger2
@Profile(value = {"dev","test", "hlytest", "fytest","hztest"})
public class SwaggerConfig implements WebMvcConfigurer {

    @Value("${swagger.title}")
    private String swaggerTitle;

    @Bean
    public Docket createPlatAdmin() {
        return DocketUtils.createPlatAdmin(swaggerTitle);
    }
    @Bean
    public Docket createPlatAdminCommodity() {
        return DocketUtils.createPlatAdminCommodity(swaggerTitle);
    }
    @Bean
    public Docket createPlatAdminFoundation() {
        return DocketUtils.createPlatAdminFoundation(swaggerTitle);
    }
    @Bean
    public Docket createPlatAdminMerchant() {
        return DocketUtils.createPlatAdminMerchant(swaggerTitle);
    }
    @Bean
    public Docket createPlatAdminStock() {
        return DocketUtils.createPlatAdminStock(swaggerTitle);
    }
    @Bean
    public Docket createPlatAdminTrade() {
        return DocketUtils.createPlatAdminTrade(swaggerTitle);
    }
    @Bean
    public Docket createPlatAdminUser() {
        return DocketUtils.createPlatAdminUser(swaggerTitle);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
