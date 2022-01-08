package com.evol.listener;

import com.evol.domain.request.OrderCancelParam;
import com.evol.service.NetOrderService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author admin
 */
@Component
public class MsgRocketConsumer {

    @Service
    @RocketMQMessageListener(topic = "topic-order-cancel", consumerGroup = "order-consumer")
    public class RocketMqUpdateBalanceEventConsumer1 implements RocketMQListener<OrderCancelParam>{

        @Autowired
        private NetOrderService netOrderService;

        /**
         * 封装过，无异常会自动ack，有异常mq重发
         * @param orderCancelParam
         */
        @SneakyThrows
        @Override
        public void onMessage(OrderCancelParam orderCancelParam) {
            System.out.println("接受到消息为:RocketMqUpdateBalanceEventConsumer1");
            if(!netOrderService.expireCanceledOrder(orderCancelParam)){
                throw new Exception("未能取消订单");
            }

        }
    }

}