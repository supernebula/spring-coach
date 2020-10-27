package com.evol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CoachBusinessServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoachBusinessServerApplication.class, args);
    }

}
