package com.evol.controller;

import com.evol.domain.model.User;
import com.evol.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("add")
    public User addUser(@RequestParam String username, @RequestParam  String password){
        User user = userService.addUser(username, password);
        return user;
    }

    @GetMapping("test")
    public String test(){
        BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
        boolean zhangsanPwdOK = pwdEncoder.matches("123456", "$2a$10$xGMEhlJTF/v2jnqLxE8MausOzoHf3sB7jTNi" +
                "/kgY2ev1DYtQA4kdG");
        boolean lisiPwd = pwdEncoder.matches("123456", "$2a$10$d4LZnBeRdfzRNOWJ5V21qO9ZOn.8O.j/5bJbAjJ96VRPaaslqQ2nK");

        log.info("zhangsanPwdOK:" + zhangsanPwdOK);
        log.info("lisiPwd:" + lisiPwd);

        return "test1";
    }

}
