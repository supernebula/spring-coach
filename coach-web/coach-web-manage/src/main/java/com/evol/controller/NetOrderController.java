package com.evol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author admin
 */
@RequestMapping("netOrder")
@Controller
public class NetOrderController {

    @GetMapping("index")
    public String index(){
        return "netorder/index";
    }

    @GetMapping("create")
    public String create(){
        return "netorder/create";
    }

    @GetMapping("edit")
    public String edit(){
        return "netorder/edit";
    }

    @GetMapping("detail")
    public String detail(){
        return "netorder/detail";
    }

}
