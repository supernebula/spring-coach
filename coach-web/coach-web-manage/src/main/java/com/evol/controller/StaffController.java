package com.evol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author admin
 */
@RequestMapping("staff")
@Controller
public class StaffController {

    @GetMapping("index")
    public String index(){
        return "staff/index";
    }

    @GetMapping("create")
    public String create(){
        return "staff/create";
    }

    @GetMapping("edit")
    public String edit(){
        return "staff/edit";
    }

    @GetMapping("detail")
    public String detail(){
        return "staff/detail";
    }

    @GetMapping("changePwd")
    public String changePwd(){
        return "staff/changePwd";
    }

}
