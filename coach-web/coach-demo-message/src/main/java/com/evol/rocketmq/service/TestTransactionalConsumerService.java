package com.evol.rocketmq.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RocketMQMessageListener(topic = "spring-transaction-topic", consumerGroup = "string_trans_consumer")
public class TestTransactionalConsumerService implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        log.info(TestTransactionalConsumerService.class.getName() + "接受到：%s", message);
    }
}
