package com.evol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.sql.DataSource;
import java.sql.Connection;

@ServletComponentScan //启动时会扫描@WebServlet、@WebFilter和@WebListener注解，并创建该类的实例
@SpringBootApplication
public class CaochDemoApplication {
    public static void main(String[] args) {
        System.out.println("SpringBootApplication 运行run之前");
        SpringApplication.run(CaochDemoApplication.class, args);
        System.out.println("SpringBootApplication 运行run之后");
    }
}


