package com.evol.listener;

import com.evol.domain.request.UpdateUserBalanceParam;
import com.evol.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author admin
 */
public class MsgRocketConsumer {

    @Slf4j
    @Service
    @RocketMQMessageListener(topic = "test-topic-1", consumerGroup = "my-consumer_test-topic-1")
    public class RocketMqStringConsumer implements RocketMQListener<String>{
        @Override
        public void onMessage(String message) {
            log.info("received message: {}", message);
        }
    }

    @Slf4j
    @Service
    @RocketMQMessageListener(topic = "test-topic-2", consumerGroup = "my-consumer_test-topic-2")
    public class RocketMqEventConsumer1 implements RocketMQListener<UpdateUserBalanceParam>{


        @Autowired
        private UserService userService;

        /**
         * 封装过，无异常会自动ack，有异常mq重发
         * @param balanceParam
         */
        @Override
        public void onMessage(UpdateUserBalanceParam balanceParam) {
            log.info("received message: {}", balanceParam );
            userService.updateUserBalance(balanceParam);
        }
    }

}
