package com.gs.lshly.middleware.oss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 常量类，读取配置文件application.properties中的配置
 */
@Data
@ConfigurationProperties("aliyun.oss.file")
public class AliyunOSSProperties {

    private String endpoint;

    private String keyId;

    private String keySecret;

    private String fileHost;

    private String bucketName;

}