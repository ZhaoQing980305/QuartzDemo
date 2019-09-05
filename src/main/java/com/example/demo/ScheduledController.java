package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@Configuration
@EnableScheduling
@PropertySource("classpath:/schedule.properties")
public class ScheduledController {

    @Scheduled(cron = "${jobs.runTime}")
    public void scheduled() {
        System.out.println("每五分钟执行一次------->当前时间："+getDate());
    }


    private String getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

}
