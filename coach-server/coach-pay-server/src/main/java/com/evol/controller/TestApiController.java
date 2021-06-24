package com.evol.controller;


import com.evol.service.feign.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/testApi")
@RestController
public class TestApiController {

    @Autowired
    UserClient userClient;

    @GetMapping("/get")
    public Object get(){
        return "object1";
    }

//    @GetMapping("/payuser")
//    public Object payUser(){
//        List<User> users = userClient.getUsers();
//        return users;
//    }



}
