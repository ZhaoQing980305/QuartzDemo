package com.example.demo;

import com.example.demo.dao.ScheduleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Component
@Configuration
@EnableScheduling
public class ScheduleConfig implements SchedulingConfigurer {
    @Autowired
    private ScheduleDao scheduleDao;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(
                // 1.添加任务内容(Runnable)
                () -> {
                    // 1.1具体任务
                    System.out.println("定时任务从数据库中获取------>当前时间： " + LocalDateTime.now().toLocalTime());
                },
                // 2.设置执行周期(Trigger)
                triggerContext -> {
                    // 2.1 从数据库获取执行周期
                    String cron = scheduleDao.findAll().get(0).getCron();
                    // 2.2 合法性校验.
                    if (StringUtils.isEmpty(cron)) {
                        // 根据需求修改
                    }
                    // 2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                });

    }
}
