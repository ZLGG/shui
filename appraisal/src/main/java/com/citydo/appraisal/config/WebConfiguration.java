package com.citydo.appraisal.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "appraisal.oath")
public class WebConfiguration implements WebMvcConfigurer {

    private List<String> whiteUrls;

    public List<String> getWhiteUrls() {
        return this.whiteUrls;
    }

    public void setWhiteUrls(List<String> whiteUrls) {
        if (whiteUrls != null) {
            this.whiteUrls = whiteUrls;
        }
    }

    @Bean
    public MyAuthInterceptor globalUserInterceptor() {
        return new MyAuthInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(globalUserInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(whiteUrls);
    }
}
