package com.evol.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetTestController {

    @PreAuthorize("hasAuthority('user:create')")
    public String createUser(String username, String password){
        return "验证通过";
    }

}
