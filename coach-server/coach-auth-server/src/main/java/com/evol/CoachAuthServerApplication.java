package com.evol;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.evol.mapper")
public class CoachAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoachAuthServerApplication.class, args);
    }

}
