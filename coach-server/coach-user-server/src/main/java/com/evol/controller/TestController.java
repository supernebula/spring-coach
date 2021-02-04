package com.evol.controller;

import com.evol.filter.OauthAccess;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {

    @GetMapping("/index")
    public Object index(){
        return "testindex";
    }

    @OauthAccess(token = false)
    @RequestMapping(value = "/testAspect",  produces = "application/json;charset=UTF-8")
    public Object testAspect(){
        return "test aspect";
    }

}
