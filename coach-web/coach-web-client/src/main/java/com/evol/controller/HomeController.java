package com.evol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@CrossOrigin
public class HomeController {

    @GetMapping("wechat/wxCheck")
    public String wxCheck(String token){
        return token;
    }

    @CrossOrigin
    @GetMapping("wechat/reqCode")
    public String wechatReqCode(){
        return "/wechat/reqCode";
    }

    @CrossOrigin
    @GetMapping("wechat/reqToken")
    public String wechatReqToken(){
        return "/wechat/reqToken";
    }

    @CrossOrigin
    @GetMapping({"index", "/", ""})
    public String index(Model model){
        return "/movie/index";
    }

    @CrossOrigin
    @GetMapping({"/movie/detail"})
    public String detail(Model model){
        return "/movie/detail";
    }


    @CrossOrigin
    @GetMapping({"/order/pay"})
    public String pay(Model model){
        return "/order/pay";
    }

    @CrossOrigin
    @GetMapping({"/payResult"})
    public String payResult(Model model){
        return "/order/payResult";
    }
}
