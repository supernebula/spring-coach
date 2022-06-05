package com.evol.bootaddin;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String ... arg) throws Exception{
        System.out.println("MyCommandLineRunner正在执行run");
    }
}
