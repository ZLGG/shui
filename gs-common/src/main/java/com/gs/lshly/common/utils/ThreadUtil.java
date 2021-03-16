package com.gs.lshly.common.utils;

import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

public class ThreadUtil {

    /**
     *
     * @param corePoolSize 核心线程数
     * @param maxPoolSize 最大线程数
     * @param queueCapacity 队列大小
     * @param keepAliveSeconds 线程最大空闲时间
     * @param threadNamePrefix 线程名称前缀
     * @return
     */
    public static TaskExecutor createExecutor(int corePoolSize, int maxPoolSize, int queueCapacity, int keepAliveSeconds, String threadNamePrefix) {
        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

}
