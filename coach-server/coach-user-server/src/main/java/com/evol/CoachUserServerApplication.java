package com.evol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CoachUserServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoachUserServerApplication.class, args);
    }

}
