package com.evol.trans.controller;

import com.evol.trans.service.TransTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TranTestController {

    @Autowired
    private TransTestService transTestService;

    @RequestMapping("/transTest")
    public String transTest(){
        transTestService.insertUser();
        return "success";
    }

}
