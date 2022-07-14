//package com.evol.kafka.controller;
//
//import com.evol.kafka.KafkaProducer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequestMapping("/kafka")
//@RestController
//public class KafkaController {
//
//    @Autowired
//    private KafkaProducer kafkaProducer;
//
//
//    @GetMapping(value = "/send")
//    public void sendMsg(){
//        kafkaProducer.send("this is a test kafka topic message!");
//    }
//}
