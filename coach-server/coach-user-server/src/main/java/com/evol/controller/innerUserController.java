package com.evol.controller;

import com.evol.base.client.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "用户列表")
@RequestMapping("users")
@RestController
public class innerUserController {



    @ApiOperation(value = "查询用户列表v",notes = "查询用户列表n")
    @GetMapping("list")
    public List<User> list(){
        List<User> list = new ArrayList<User>();
        list.add(new User(1, "name1", "13688885555", "地址1"));
        list.add(new User(1, "name2", "17811113333", "地址2"));
        list.add(new User(1, "name3", "13814244445", "地址3"));
        return list;
    }

    @ApiOperation(value = "查询用户列表v",notes = "查询用户列表n")
    @GetMapping("listTime")
    public List<User> listTime() throws InterruptedException {
        List<User> list = new ArrayList<User>();
        list.add(new User(1, "nameTime1", "13688885555", "地址Time1"));
        list.add(new User(1, "nameTime2", "17811113333", "地址Time2"));
        list.add(new User(1, "nameTime3", "13814244445", "地址Time3"));
        Thread.sleep(1000);
        return list;
    }

    @PostMapping("upsert")
    public User upsert(User user, Long userId){
        if(user == null){
            return  null;
        }
        user.setAddress(user.getAddress() + "_upsert");
        return user;
    }

}
