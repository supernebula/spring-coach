package com.evol.controller;

import com.evol.domain.model.User;
import com.evol.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("user/add")
    @ResponseBody
    public User addUser(@RequestParam String username, @RequestParam  String password){
        User user = userService.addUser(username, password);
        return user;
    }

    @GetMapping("user/test")
    @ResponseBody
    public String test(){
        BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
        boolean zhangsanPwdOK = pwdEncoder.matches("123456", "$2a$10$xGMEhlJTF/v2jnqLxE8MausOzoHf3sB7jTNi" +
                "/kgY2ev1DYtQA4kdG");
        boolean lisiPwd = pwdEncoder.matches("123456", "$2a$10$d4LZnBeRdfzRNOWJ5V21qO9ZOn.8O.j/5bJbAjJ96VRPaaslqQ2nK");

        log.info("zhangsanPwdOK:" + zhangsanPwdOK);
        log.info("lisiPwd:" + lisiPwd);

        return "test1";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/index")
    public String index(ModelMap modelMap){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        modelMap.put("user",principal);
        return "index";
    }

    //@PreAuthorize("hasRole('admin')")
    //@Secured({"user.test"})
    @Secured("ROLE_admin")
    @RequestMapping("/userTest")
    public String userTest(){
        return "indexTest";
    }



    @RequestMapping("/failure")
    public String failure(){
        return "failure";
    }


}
