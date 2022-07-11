package com.evol.rabbitmq.receiver;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 配置消息消费者 接收消息
 */
@Component
public class DirectReceiver {

    // 处理接收到的消息
    // 注意参数需要消息发送者发送的消息的类型保持一致
    @RabbitHandler
    //监听queues内的队列名称列表
    @RabbitListener(queues = {"my-queue"})
    public void handler(Message message) {
        System.out.println("接收消息>>>"+new String(message.getBody()));
    }

    @RabbitListener(bindings = {@QueueBinding(value = @Queue("my-queue"), exchange = @Exchange("my-exchange"), key = {"my-routing-key"})})
    public void receiveMsg(String message){
        System.out.println("接收消息>>>"+ message);
        //todo json反序列化， 业务逻辑等
    }

    @RabbitListener(bindings = {@QueueBinding(value = @Queue("my-queue"), exchange = @Exchange("my-exchange"), key = {"my-routing-key"})})
    public void receiveMsg2(String message){
        System.out.println("接收消息>>>"+ message);
        //todo json反序列化， 业务逻辑等
    }

}
