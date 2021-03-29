package com.evol.controller;

import com.evol.contant.RabbitContants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/testMqUser")
    public String testMqUser(String message){
        rabbitTemplate.convertAndSend(RabbitContants.USER_BALANCE_EXCHANGE, RabbitContants.USER_BALANCE_ROUTING_KEY,
                "message" + message);
        return message;
    }
}
