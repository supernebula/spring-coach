package com.evol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author admin
 */
@RequestMapping("movie")
@Controller
public class MovieController {

    @GetMapping("index")
    public String index(){
        return "movie/index";
    }

    @GetMapping("create")
    public String create(){
        return "movie/create";
    }

    @GetMapping("edit")
    public String edit(){
        return "movie/edit";
    }

    @GetMapping("detail")
    public String detail(){
        return "movie/detail";
    }

}
