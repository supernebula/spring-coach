package com.evol.controller;

import com.evol.sender.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 */
@RestController
public class IndexController {

    @Autowired
    MessageSender messageSender;

    @RequestMapping("/index")
    public String index(){
        messageSender.send("hello world!");
        return "SUCCESS";
    }

}
