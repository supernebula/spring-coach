package com.evol;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.evol.mapper")
public class CoachWebExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoachWebExampleApplication.class, args);
    }

}
