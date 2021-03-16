package com.gs.lshly.biz.support.commodity.config;


import com.gs.lshly.common.utils.ThreadUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 异步调用线程池配置
 * @author lxus
 * @date 2020/8/28
 */
@Configuration
@EnableAsync
public class ThreadPoolExecutorConfig {
    @Bean
    public TaskExecutor executor(){
        return ThreadUtil.createExecutor(10, 20, 200, 60, "commodity-Executor-");
    }

}
