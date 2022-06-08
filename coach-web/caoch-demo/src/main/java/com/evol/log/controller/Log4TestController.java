package com.evol.log.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Log4TestController {

//log4j 开始====

Logger logger = LoggerFactory.getLogger(Log4TestController.class);
//
//    /**
//     * 注意：测试log4j2时，有配置文件冲突，修改掉logback-spring.xml的默认文件名
//     * @return
//     */
//    @RequestMapping("/log4Index")
//    public String Index(){
//        logger.info("请求了Log4Index");
//        return "log4j";
//    }
// log4j 结束====


    @RequestMapping("/logbackIndex")
    public String logbackIndex(){
        logger.info("logback整合成功了");
        logger.error("logback整合成功了");
        return "logback SUCCESS";
    }
}
