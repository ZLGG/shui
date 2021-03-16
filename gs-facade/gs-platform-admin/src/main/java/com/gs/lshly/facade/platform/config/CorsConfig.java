package com.gs.lshly.facade.platform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "HEAD", "OPTIONS", "DELETE", "PUT")
                .maxAge(36000)
                .allowedHeaders("*")
                .exposedHeaders("refresh_token");

    }
}
