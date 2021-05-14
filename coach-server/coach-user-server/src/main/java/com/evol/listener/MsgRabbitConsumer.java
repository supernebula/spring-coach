package com.evol.listener;

import com.evol.contant.RabbitContants;
import com.evol.domain.request.UpdateUserBalanceParam;
import com.evol.service.UserService;
import com.evol.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import com.rabbitmq.client.Channel;

import java.io.IOException;

/**
 * https://blog.csdn.net/xmtblog/article/details/114696336
 */
@Component
@Slf4j
public class MsgRabbitConsumer {

    @Autowired
    private UserService userService;

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue(RabbitContants.USER_BALANCE_QUEUE), exchange = @Exchange(RabbitContants.USER_BALANCE_EXCHANGE), key = {RabbitContants.USER_BALANCE_ROUTING_KEY})
    })
    public void receiveMsg(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {

        log.info("接受到消息为: "+message);
        try{
            UpdateUserBalanceParam balanceParam = JsonUtil.ParseObject(message, UpdateUserBalanceParam.class);
            userService.updateUserBalance(balanceParam);
            channel.basicAck(tag, false);
        }catch (Exception e){
            log.error("接受消息出错");
            e.printStackTrace();
            channel.basicNack(tag,false,true);
        }
        log.info("rabbit消息处理完毕");
    }
}