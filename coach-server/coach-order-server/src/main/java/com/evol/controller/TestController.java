package com.evol.controller;

import com.evol.contant.RabbitContants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/testMqUser")
    public String testMqUser(String message){
        rabbitTemplate.convertAndSend(RabbitContants.USER_BALANCE_EXCHANGE, RabbitContants.USER_BALANCE_ROUTING_KEY,
                "message" + message);
        return message;
    }

    @GetMapping("/testLog")
    public String testLog(){
        String message = "test log" + System.currentTimeMillis();
        logger.debug(message);
        logger.info(message);
        return message;
    }
}
