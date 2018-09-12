package com.xiguo.www.group.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务服务类
 * 默认10个线程.避免定时任务线程阻塞导致任务无法运行
 * @author: ZGC
 * @date Created in 2018/9/8 下午 12:41
 */
@Slf4j
@Component
public class ScheduledService {

    @Autowired
    WeCharService weCharService;

    @Scheduled(fixedRate = 7000000)
    public void scheduled1() {
        weCharService.updateAccessToken();
    }

//    @Scheduled(fixedDelay = 5000)
//    public void scheduled2() {
//    }

//    @Scheduled(cron = "0 0 0/1 * * ?")
//    public void scheduled(){
//    }
}
