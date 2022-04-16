package com.evol.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/data")
    public String data(){
        return "This is data;";
    }

}
