package com.evol.controller;

import com.evol.contant.RabbitContants;
import com.evol.domain.request.OrderCancelParam;
import com.evol.service.NetOrderService;
import com.evol.util.RedisCommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RedisCommonUtil redisCommonUtil;

    @Autowired
    private NetOrderService netOrderService;

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

    private String atomicLockKey = "20210330";

    @PostMapping("/lockTest")
    public String lockTest(){
        boolean lock = redisCommonUtil.atomicLock(atomicLockKey);
        if(lock){
            logger.debug("lockTest: 锁定中");
        }
        return lock ? "lock ok" : "lock fail";

    }

    @PostMapping("/unlockTest")
    public String unlockCheckTest(){
        boolean flag = redisCommonUtil.delete("redis_lock" + atomicLockKey);
        return "unlock:" + flag;
    }

    @GetMapping("/testCancelDelayOrder")
    public String cancelDelayOrderTest(){
        netOrderService.cancelDelayNotPaidOrder(1, "232342323", new Date());
        return "cancelDelayOrderTest";
    }
}
