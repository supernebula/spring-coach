package com.evol.rocketmq;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author admin
 */
@Component
@Slf4j
public class MsgRocketConsumer {


    @Service
    @RocketMQMessageListener(topic = "topic-order-cancel", consumerGroup = "order-consumer", selectorExpression = "tag1 || tag2 || tag3")
    public class RocketMqUpdateBalanceEventConsumer1 implements RocketMQListener<DemoOrderDto>{


        /**
         * 封装过，无异常会自动ack，有异常mq重发
         * @param orderCancelParam
         */
        @SneakyThrows
        @Override
        public void onMessage(DemoOrderDto orderCancelParam) {
            System.out.println("接受到消息为:RocketMqUpdateBalanceEventConsumer1");
            //todo 关于orderCancelParam的业务逻辑；
        }
    }

    @Service
    @RocketMQMessageListener(topic = TopicConstant.DEMO_TOPIC1, consumerGroup = "demo-group1", selectorExpression =
            "tag1 || tag2")
    public class RocketMqUpdateBalanceEventConsumer2 implements RocketMQListener<String>{


        /**
         * 封装过，无异常会自动ack，有异常mq重发
         * @param msg
         */
        @SneakyThrows
        @Override
        public void onMessage(String msg) {
            System.out.println("接受到消息为:RocketMqUpdateBalanceEventConsumer2:" + msg);

            //todo 关于orderCancelParam的业务逻辑；
        }
    }

}