package com.evol.rocketmq.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQReplyListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RocketMQMessageListener(topic = "${evol.rocketmq.topic}", consumerGroup = "my-consumer_test-topic")
public class TestStringTagConsumerService implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        log.info(TestStringTagConsumerService.class.getName() + "接受到消息====>" + message);
    }
}
