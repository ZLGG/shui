package com.gs.lshly.facade.merchant.config;

import com.gs.lshly.middleware.log.IAccessLogService;
import com.gs.lshly.rpc.api.common.IAccessLogRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 日志配置
 * @author lxus
 * @since 2020-12-20
 */
@Configuration
public class AccessLogConfig {

    @DubboReference
    IAccessLogRpc rpc;

    @Bean("AccessLogService")
    public IAccessLogService logService() {
        return dto -> rpc.save(dto);
    }

}
