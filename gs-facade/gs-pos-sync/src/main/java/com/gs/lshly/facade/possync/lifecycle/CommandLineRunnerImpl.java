package com.gs.lshly.facade.possync.lifecycle;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        timerDaemon();
    }
    private void timerDaemon(){
        //简单的定时任务
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

            }
        };
        // 启动定时任务，每10秒钟消费一次
        new Timer("qmcs-timer", true).schedule(task, 10 * 1000, 10 * 1000);
    }
}