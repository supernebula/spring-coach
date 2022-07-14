package com.evol.controller;

import com.evol.rocketmq.DemoOrderDto;
import com.evol.rocketmq.RocketMQSender;
import com.evol.rocketmq.TopicConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

@RestController
public class DemoMQController {

    @Autowired
    private RocketMQSender rocketMQSender;

    @GetMapping("/testSendMsg")
    public String testSendMessage(@RequestParam(required = false) String msg){
        if(StringUtils.isBlank(msg)){
            msg = "Hello World";
        }
        rocketMQSender.sendMessage(TopicConstant.DEMO_TOPIC1, msg);
        return "ok";
    }

    @GetMapping("/testSendObj")
    public String testSendDto(){
        DemoOrderDto demoOrderDto = new DemoOrderDto("OR202204051220", 100, new Date());
        Object result = rocketMQSender.sendDto(TopicConstant.DEMO_TOPIC1, demoOrderDto);
        return "ok" + result;
    }
}
