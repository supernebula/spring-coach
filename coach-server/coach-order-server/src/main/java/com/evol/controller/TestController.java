package com.evol.controller;

import com.evol.contant.RabbitContants;
import com.evol.domain.request.OrderCancelParam;
import com.evol.service.NetOrderService;
import com.evol.util.RedisCommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RedisCommonUtil redisCommonUtil;

    @Autowired
    private NetOrderService netOrderService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

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

    @GetMapping("/testUpdateUserBalance")
    public String testUpdateUserBalance(){
        netOrderService.updateUserBalance(1, 100, "OD000011112244450000");
        return "OK";
    }

    @GetMapping("/testCancelDelayOrder")
    public String cancelDelayOrderTest(){
        netOrderService.cancelDelayNotPaidOrder(1, "232342323", new Date());
        return "cancelDelayOrderTest";
    }

    @GetMapping("/testRocketmqDelay")
    public String rocketMQDelayTest(){
        //rocketMQ 发送
        // delayLevel=1  2  3   4   5  6  7  8  9  10 11 12 13 14  15  16  17 18
        // delayTime =1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
        // 设置延迟延迟级别延迟30s
        String jsonStr = "{id:1, name:\"zhangSan-delay\"}";
        SendResult sendResult = rocketMQTemplate.syncSend("test-topic-2", MessageBuilder.withPayload(jsonStr).build(),
                1000, 2);
        if(!sendResult.getSendStatus().equals(SendStatus.SEND_OK)){
            log.error("延迟消息发送失败：{0}" , sendResult.getSendStatus().toString());
            throw new RuntimeException("延迟消息发送失败：" +sendResult.getSendStatus().toString());
        }
        return sendResult.getSendStatus().toString();
    }

    @GetMapping("/testRocketmq")
    public String rocketMQTest(){
        String jsonStr = "{id:1, name:\"zhangSan\"}";

        rocketMQTemplate.convertAndSend("test-topic-1", jsonStr);
        return "ok";
    }


}
