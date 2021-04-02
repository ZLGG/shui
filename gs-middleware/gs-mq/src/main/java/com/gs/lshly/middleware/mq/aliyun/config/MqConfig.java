package com.gs.lshly.middleware.mq.aliyun.config;


import com.aliyun.openservices.ons.api.PropertyKeyConst;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Data
@Configuration
@ConfigurationProperties("aliyun.mq")
public class MqConfig {
    private String accessKey;
    private String secretKey;
    private String nameSrvAddr;
    private String instanceId;
    private String topic;
    private String groupId;
    private String tag;
    private String timeTopic;
    private String timeGroupId;
    private String timeTag;
    private String time;

    public Properties getMqPropertie() {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.AccessKey, this.accessKey);
        properties.setProperty(PropertyKeyConst.SecretKey, this.secretKey);
        properties.setProperty(PropertyKeyConst.NAMESRV_ADDR, this.nameSrvAddr);
        //设置发送超时时间，单位毫秒
        properties.setProperty(PropertyKeyConst.SendMsgTimeoutMillis, "4000");
        return properties;
    }

}
