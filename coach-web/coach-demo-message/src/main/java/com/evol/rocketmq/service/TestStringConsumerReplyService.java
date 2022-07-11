package com.evol.rocketmq.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQReplyListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RocketMQMessageListener(topic = "${stringRequestTopic}", consumerGroup = "stringRequestConsumer")
public class TestStringConsumerReplyService implements RocketMQReplyListener<String, String> {
    @Override
    public String onMessage(String message) {
        log.info(TestStringConsumerReplyService.class.getName() + "接受消息：%s", message);
        return "reply string";
    }
}
