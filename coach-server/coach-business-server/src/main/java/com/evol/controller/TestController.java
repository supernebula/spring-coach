package com.evol.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {

    @GetMapping("/info")
    public Object info(){

        return "abcd";

    }

    @GetMapping("/index")
    public Object index(){
        return "testindex";
    }


}
