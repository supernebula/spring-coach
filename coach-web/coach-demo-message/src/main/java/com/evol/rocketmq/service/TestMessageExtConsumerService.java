package com.evol.rocketmq.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.UtilAll;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RocketMQMessageListener(topic = "${evol.rocketmq.msgExtTopic}", selectorExpression = "tag0 || tag1", consumerGroup = "${spring.application.name}-messge-ext-consumer")
public class TestMessageExtConsumerService implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {

    //实现消息的消费处理
    @Override
    public void onMessage(MessageExt message) {
        log.info(TestMessageExtConsumerService.class.getName() + "获取消息，消息Id: %s, 消息体： %s", message.getMsgId(), new String(message.getBody()));
    }

    //设置从当前时间点开始消费消息
    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_TIMESTAMP);
        consumer.setConsumeTimestamp(UtilAll.timeMillisToHumanString3(System.currentTimeMillis()));
    }
}
