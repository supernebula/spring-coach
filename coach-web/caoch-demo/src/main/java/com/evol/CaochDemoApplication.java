package com.evol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan //启动时会扫描@WebServlet、@WebFilter和@WebListener注解，并创建该类的实例
@SpringBootApplication
public class CaochDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaochDemoApplication.class, args);
    }

}
