package com.evol.rocketmq;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Slf4j
public class RocketMQSender {

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    /**
     *
     * @param topic @link TopicConstant.DEMO_TOPIC1
     * @param message
     */
    public void sendMessage(String topic, String message){
        SendResult result = rocketMQTemplate.syncSend(topic, message);
        log.info("发送结果：{}", JSON.toJSONString(result));
    }

    public void sendTagMessage(String topic, String tag, String message){
        SendResult result = rocketMQTemplate.syncSend(topic + ":" + tag, message);
        log.info("发送结果：{}", JSON.toJSONString(result));
    }

    public <T extends Serializable> SendStatus sendDto(String topic, T dto){
        SendResult result = rocketMQTemplate.syncSend(topic, JSON.toJSONString(dto));
        log.info("发送结果：{}", JSON.toJSONString(result));
        return result.getSendStatus();
    }

}
