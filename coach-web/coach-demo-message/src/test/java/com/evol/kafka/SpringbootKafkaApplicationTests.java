//package com.evol.kafka;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
///**
// * 通过junit测试用例，启动一个Kafka Server服务，包含如下4个Broker节点：
// * value: Broker节点数量。
// * count: 同value作用一样，互为别名。
// * ports：指定多个端口。
// * controlledShutdown: 控制关闭开关，主要用于当Broker意外关闭时减少此Broker上 Partition的不可用时间。
// */
//@SpringBootTest
//@EmbeddedKafka(count=4, prots={9092,9093,9094,9095})
//public class SpringbootKafkaApplicationTests {
//    @Autowired
//    KafkaTemplate kafkaTemplate;
//
//    @Autowired
//    EmbeddedKafkaBroker broker;
//
//    @Test
//    void contextLoads() throws IOException{
//        System.out.println(broker.getBrokersAsString());
//        System.out.println(kafkaTemplate);
//        //测试方法不支持控台输入，会导致程序一直阻塞，如果不写，程序就直接退出了
//        System.in.read();
//    }
//}
