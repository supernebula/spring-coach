package com.evol.listener;

import com.evol.contancts.enums.NetOrderStatusEnum;
import com.evol.contant.RabbitContants;
import com.evol.domain.model.NetOrder;
import com.evol.domain.request.OrderCancelParam;
import com.evol.domain.request.UpdateUserBalanceParam;
import com.evol.service.NetOrderService;
import com.evol.util.JsonUtil;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class MsgRabbitConsumer {

    @Autowired
    private NetOrderService netOrderService;

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue(RabbitContants.ORDER_DELAY_CANCEL_QUEUE), exchange =
            @Exchange(RabbitContants.ORDER_DELAY_CANCEL_EXCHANGE), key = {RabbitContants.ORDER_DELAY_CANCEL_ROUTING_KEY})
    })
    public void receiveMsg(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {

        log.info("接受到消息为: "+message);
        try{
            OrderCancelParam orderCancelParam = JsonUtil.ParseObject(message, OrderCancelParam.class);
            if(netOrderService.expireCanceledOrder(orderCancelParam)){
                channel.basicAck(tag, true);
                return;
            }
            channel.basicNack(tag,false,true);
        }catch (Exception e){
            log.error("接受消息出错");
            e.printStackTrace();
            channel.basicNack(tag,false,true);
        }
        log.info("rabbit消息处理完毕");
    }
}