package com.gs.lshly.middleware.oss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 常量类，读取配置文件application.properties中的配置
 */
@Data
@ConfigurationProperties("local.oss.file")
public class LocalFileProperties {

    private String fileDir;

    private String bucketName;

    private String fileHost;

    private String module;

}