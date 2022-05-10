package com.evol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.evol.mapper")
public class CoachWebOauthExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoachWebOauthExampleApplication.class, args);
    }

}
