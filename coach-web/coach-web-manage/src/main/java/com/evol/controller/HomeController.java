package com.evol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping({"/home/index", "/index", "/"})
    public String index() {
        return "home/index";
    }

    @GetMapping("/default0")
    public String default0(){
        return "home/default0";
    }

    @GetMapping("/default1")
    public String default1(){
        return "home/default1";
    }

    @GetMapping("/default2")
    public String default2(){
        return "home/default2";
    }


    @RequestMapping({"testErr"})
    public String testErr(){

        throw new RuntimeException();
    }
}
