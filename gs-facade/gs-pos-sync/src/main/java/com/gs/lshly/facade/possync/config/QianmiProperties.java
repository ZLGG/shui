package com.gs.lshly.facade.possync.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Data
public class QianmiProperties {

    @Value("${qianmi.redirect}")
    private String redirectURI;

    @Value("${qianmi.url}")
    private String serverUrl;

    @Value("${qianmi.key}")
    private String key;

    @Value("${qianmi.secret}")
    private String secret;


}
