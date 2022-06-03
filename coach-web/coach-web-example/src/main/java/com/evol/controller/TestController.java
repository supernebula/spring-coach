package com.evol.controller;

import com.evol.config.UserCustomConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private UserCustomConfig userCustomConfig;

    @GetMapping("/test/propTest")
    public String propTest(){
        return userCustomConfig.getName();
    }
}
