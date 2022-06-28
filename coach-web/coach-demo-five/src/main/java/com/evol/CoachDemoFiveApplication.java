package com.evol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching //开启基于注解的缓存
@SpringBootApplication
public class CoachDemoFiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoachDemoFiveApplication.class, args);
    }

}
