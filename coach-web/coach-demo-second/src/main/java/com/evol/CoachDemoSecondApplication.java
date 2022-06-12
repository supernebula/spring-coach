package com.evol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
public class CoachDemoSecondApplication implements ApplicationRunner {
    @Autowired
    private DataSource dataSource;
    @Override
    public void run(ApplicationArguments args) throws Exception{
        try (Connection conn = dataSource.getConnection()){
            System.out.println("打印数据源名称");
            System.out.println(conn.toString());
        }
    }
    public static void main(String[] args) {
        SpringApplication.run(CoachDemoSecondApplication.class, args);
    }
}
