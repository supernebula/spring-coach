package com.evol;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.evol.mapper")
public class CoachWeb2ManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoachWeb2ManageApplication.class, args);
    }

}
