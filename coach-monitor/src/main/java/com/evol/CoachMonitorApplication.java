package com.evol;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableAutoConfiguration
@EnableAdminServer
@EnableEurekaClient
public class CoachMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoachMonitorApplication.class, args);
    }

}

