package com.gs.lshly.facade.merchant.config;

import com.gs.lshly.common.utils.DocketUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * SwaggerConfig
 *
 * @author lxus
 * @since 2020/9/14
 */
@Configuration
@Profile(value = {"dev", "test", "hlytest", "fytest"})
public class SwaggerConfig implements WebMvcConfigurer {

    @Value("${swagger.title}")
    private String swaggerTitle;

    @Bean
    public Docket createMerchantAdmin() {
        return DocketUtils.createMerchantAdmin(swaggerTitle);
    }
    @Bean
    public Docket createMerchantAdminPCFoundation() {return DocketUtils.createMerchantAdminPCFoundation(swaggerTitle);}
    @Bean
    public Docket createMerchantAdminPCCommodity() {
        return DocketUtils.createMerchantAdminPCCommodity(swaggerTitle);
    }
    @Bean
    public Docket createMerchantAdminPCMerchant() {
        return DocketUtils.createMerchantAdminPCMerchant(swaggerTitle);
    }
    @Bean
    public Docket createMerchantAdminPCStock() {
        return DocketUtils.createMerchantAdminPCStock(swaggerTitle);
    }
    @Bean
    public Docket createMerchantAdminPCTrade() {
        return DocketUtils.createMerchantAdminPCTrade(swaggerTitle);
    }
    @Bean
    public Docket createMerchantAdminPCUser() {
        return DocketUtils.createMerchantAdminPCUser(swaggerTitle);
    }
    @Bean
    public Docket createMerchantAdminH5() {
        return DocketUtils.createMerchantAdminH5(swaggerTitle);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
