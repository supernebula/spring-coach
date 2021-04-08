package com.evol;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@EnableEurekaClient
@ConditionalOnClass(CoachLogServerApplication.class)
@MapperScan("com.evol.mapper.*")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class CoachLogServerApplication  implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(CoachLogServerApplication.class, args);
    }
}

