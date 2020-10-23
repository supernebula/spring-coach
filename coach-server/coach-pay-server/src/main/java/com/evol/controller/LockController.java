package com.evol.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;

@RequestMapping("/lock")
@Controller
public class LockController {

    @Autowired
    private RedissonClient redissonClient;

    @ResponseBody
    @GetMapping("/redis")
    public Object redisTest()  {

        RLock rLock = redissonClient.getLock("rel1");

//        if(rLock.isLocked()){
////            return "锁定的：" + rLock.isLocked() + System.currentTimeMillis();
////        }

        long start =  System.currentTimeMillis();
        long end = start;

        try {
            rLock.lock(10, TimeUnit.SECONDS);
            end = System.currentTimeMillis();
            System.out.println("exec time:" + (end - start));
            Thread.sleep(3000);

        }catch (Exception ex){
            System.out.println("ex:" + ex.getMessage());

        }finally {
            rLock.unlock();
            System.out.println("rLock.unlock()");
        }

        return "rel1.isLocked" + rLock.isLocked();

    }
}
