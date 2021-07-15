package com.evol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author admin
 */
@RequestMapping("user")
@Controller
public class UserController {

    @GetMapping("index")
    public String index(){
        return "user/index";
    }

    @GetMapping("create")
    public String create(){
        return "user/create";
    }

    @GetMapping("edit")
    public String edit(){
        return "user/edit";
    }

    @GetMapping("detail")
    public String detail(){
        return "user/detail";
    }

}
