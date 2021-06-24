package com.evol.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@EnableScheduling
public class OrderTask {



    //@Scheduled(fixedRateString = "10000",initialDelay = 1000)
    public void closeNewOrder(){

        log.debug("closeNewOrder//初始延迟1秒，每隔10秒");

    }

    //从 0秒开始,每10秒执行一次
    //@Scheduled(cron = "0/10 * * * * ?")
    public void closeNewOrderAbc(){

        log.debug("closeNewOrder2从 0秒开始,每10秒执行一次");

    }

}
