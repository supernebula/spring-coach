package com.evol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@EnableEurekaClient  //nacos 和 eureka 注册中心二选一
@EnableDiscoveryClient
@EnableFeignClients
public class CoachGatewayApplication {

    //note
    public static void main(String[] args) {
        SpringApplication.run(com.evol.CoachGatewayApplication.class, args);
    }



}
