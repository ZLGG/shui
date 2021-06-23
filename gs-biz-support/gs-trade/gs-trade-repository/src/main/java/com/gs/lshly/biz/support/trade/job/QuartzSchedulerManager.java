package com.gs.lshly.biz.support.trade.job;


import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年6月18日 下午2:56:23
 */
@Configuration
public class QuartzSchedulerManager {

    // 任务调度
    @Autowired
    private Scheduler scheduler;


    /**
     * 没有支付关闭订单
     * @param time
     * @throws SchedulerException
     */
    public void closeTradeFromNoPayJob(Date time) throws SchedulerException {
    	closeTradeFromNoPayJob(scheduler,time);
        scheduler.start();
    }

    /**
     *
     * @Title: startJobTest
     * @Description: 启动 定时器 test
     * @param scheduler
     * @throws SchedulerException    参数
     * void    返回类型
     */
    private void closeTradeFromNoPayJob(Scheduler scheduler , Date time) throws SchedulerException {
        System.out.println(time + "TIME");
        // 通过JobBuilder构建JobDetail实例，JobDetail规定只能是实现Job接口的实例
        // JobDetail 是具体Job实例
        JobDetail jobDetail = JobBuilder.newJob(CloseTradeFromNoPayJob.class).withIdentity("job1", "group1").build();
        // 基于表达式构建触发器
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule();

        // CronTrigger表达式触发器 继承于Trigger
        // TriggerBuilder 用于构建触发器实例
        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger().withIdentity("job1", "group1")
                .withSchedule(simpleScheduleBuilder).startAt(time).build();
        scheduler.scheduleJob(jobDetail, simpleTrigger);
    }
}

