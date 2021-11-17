package com.evol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SystemController {

    @GetMapping("/login")
    public String index(){
        return "login";
    }

}
