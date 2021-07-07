//package com.evol.config;
//
//import com.evol.contant.RabbitContants;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.CustomExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 消息队列配置
// * Created by macro on 2018/9/14.
// */
//@Configuration
//public class RabbitMqConfig {
//
//    /**
//     * 订单延迟插件消息队列所绑定的交换机
//     */
//    @Bean
//    CustomExchange orderPluginDirect() {
//        //创建一个自定义交换机，可以发送延迟消息
//        Map<String, Object> args = new HashMap<>();
//        args.put("x-delayed-type", "direct");
//        return new CustomExchange(RabbitContants.ORDER_DELAY_CANCEL_QUEUE, "x-delayed-message",true, false,args);
//    }
//
//    /**
//     * 订单延迟插件队列
//     */
//    @Bean
//    public Queue orderPluginQueue() {
//        return new Queue(RabbitContants.ORDER_DELAY_CANCEL_QUEUE);
//    }
//
//    /**
//     * 将订单延迟插件队列绑定到交换机
//     */
//    @Bean
//    public Binding orderPluginBinding(CustomExchange orderPluginDirect, Queue orderPluginQueue) {
//        return BindingBuilder
//                .bind(orderPluginQueue)
//                .to(orderPluginDirect)
//                .with(RabbitContants.ORDER_DELAY_CANCEL_ROUTING_KEY)
//                .noargs();
//    }
//
//}