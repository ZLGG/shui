package com.gs.lshly.middleware.oss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 常量类，读取配置文件application.properties中的配置
 */
@Configuration
@EnableConfigurationProperties({AliyunOSSProperties.class, LocalFileProperties.class})
public class ConstantPropertiesUtil {

    private AliyunOSSProperties properties;

    private LocalFileProperties localFileProperties;

    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;
    public static String FILE_HOST;

    public static String LOCAL_FILE_DIR;
    public static String LOCAL_FILE_HOST;
    public static String LOCAL_BUCKET_NAME;
    public static String LOCAL_MODULE;


    @Autowired
    public ConstantPropertiesUtil(AliyunOSSProperties properties, LocalFileProperties localFileProperties) {
        this.properties = properties;
        this.localFileProperties = localFileProperties;
        END_POINT = properties.getEndpoint();
        ACCESS_KEY_ID = properties.getKeyId();
        ACCESS_KEY_SECRET = properties.getKeySecret();
        BUCKET_NAME = properties.getBucketName();
        FILE_HOST = properties.getFileHost();

        LOCAL_FILE_DIR = localFileProperties.getFileDir();
        LOCAL_FILE_HOST = localFileProperties.getFileHost();
        LOCAL_BUCKET_NAME = localFileProperties.getBucketName();
        LOCAL_MODULE = localFileProperties.getModule();
    }
    @PostConstruct
    public void init() {
        if (this.properties == null) {
            throw new RuntimeException("aliyun oss 配置错误！");
        }
        if (this.localFileProperties == null) {
            throw new RuntimeException("本地存储 配置错误！");
        }
    }

}