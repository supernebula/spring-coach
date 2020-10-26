package com.evol.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/testApi")
@RestController
public class TestApiController {

    @GetMapping("/get")
    public Object get(){
        return "object1";
    }

}
