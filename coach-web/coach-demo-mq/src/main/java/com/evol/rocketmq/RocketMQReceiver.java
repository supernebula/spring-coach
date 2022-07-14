package com.evol.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author admin
 */
@Component
@RocketMQMessageListener(topic = TopicConstant.DEMO_TOPIC1, //topic主题
        consumerGroup = "demo-group1",          //消费组
        messageModel = MessageModel.CLUSTERING,
        consumeMode = ConsumeMode.ORDERLY)
@Slf4j
public class RocketMQReceiver implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        log.info("接受到消息：{}", message);
    }
}
