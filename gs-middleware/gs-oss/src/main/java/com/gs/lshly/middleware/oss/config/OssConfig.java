package com.gs.lshly.middleware.oss.config;

import com.gs.lshly.middleware.oss.service.IFileService;
import com.gs.lshly.middleware.oss.service.imp.FileServiceImpl;
import com.gs.lshly.middleware.oss.service.imp.LocalFileServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class OssConfig {

    @Bean
    public IFileService fileService(@Value("${spring.profiles.active}") String active) {
        //富友-深圳商城采用本地存储方案
        if (
                "fytest".equalsIgnoreCase(active)
                || "fyprod".equalsIgnoreCase(active)
                || "sztest".equalsIgnoreCase(active)
                || "szprod".equalsIgnoreCase(active)
                || "hztest".equalsIgnoreCase(active)
                || "hzprod".equalsIgnoreCase(active)
        ) {
            log.info("本地方式存储-" + active);
            return new LocalFileServiceImpl();
        } else {
            log.info("阿里云对象存储-" + active);
            return new FileServiceImpl();
        }
    }
}
