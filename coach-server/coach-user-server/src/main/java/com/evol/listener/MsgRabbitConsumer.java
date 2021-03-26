package com.evol.listener;

import com.alibaba.fastjson.JSON;
import com.evol.contant.RabbitContants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MsgRabbitConsumer {



    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue(RabbitContants.MSG_QUEUE), exchange = @Exchange(RabbitContants.MSG_EXCHANGE), key = {RabbitContants.MSG_ROUTING_KEY})
    })
    public void receiveMsg(String message){
        log.info("接受到消息为: "+message);
        try{
            //业务逻辑。。。
            //....
        }catch (Exception e){
            log.error("接受消息出错");
            e.printStackTrace();
        }
        log.info("rabbit消息处理完毕");
    }
}