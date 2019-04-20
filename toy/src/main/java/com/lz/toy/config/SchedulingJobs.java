package com.lz.toy.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


@Slf4j
@Configuration
@EnableScheduling
public class SchedulingJobs {

    private final SchedulingJobsService schedulingJobsService;



    public SchedulingJobs(SchedulingJobsService schedulingJobsService

                          ) {
        this.schedulingJobsService = schedulingJobsService;

    }


   // @Scheduled(cron = "0/60 * * * * ?") // 每20秒执行一次
//    @Scheduled(cron = "0/40 * * * * ?") // 每30秒执行一次
    public void schedulerRealTime(){
        log.info("实时任务---计算");

        schedulingJobsService.sendAlarm();
    }
}
