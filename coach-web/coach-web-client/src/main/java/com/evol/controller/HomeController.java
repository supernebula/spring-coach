package com.evol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping({"index", "/", ""})
    public String index(Model model){
        return "/movie/index";
    }


    @GetMapping({"/movie/detail"})
    public String detail(Model model){
        return "/movie/detail";
    }

    @GetMapping({"/pay"})
    public String pay(Model model){
        return "/order/pay";
    }

    @GetMapping({"/payResult"})
    public String payResult(Model model){
        return "/order/payResult";
    }
}