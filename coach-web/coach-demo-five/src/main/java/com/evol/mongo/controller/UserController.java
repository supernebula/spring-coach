package com.evol.mongo.controller;

import com.evol.mongo.domain.UserDTO;
import com.evol.mongo.service.UserService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: moerhai@qq.com
 * @Date: 2020/7/12 00:49
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public List<UserDTO> findAll(){
        return userService.findAll();
    }

    @GetMapping("/get/{id}")
    public UserDTO getOne(@PathVariable Integer id){
        return userService.getOneById(id);
    }

    @PostMapping("/save")
    public UserDTO save(@RequestBody UserDTO userDTO){
        return userService.insert(userDTO);
    }

    @PostMapping("/update")
    public UpdateResult update(@RequestBody UserDTO userDTO){
        return userService.updateResult(userDTO);
    }

    @DeleteMapping("/delete/{id}")
    public DeleteResult update(@PathVariable Integer id){
        return userService.deleteResult(id);
    }

    @Cacheable(cacheNames = "all_user", key = ("'UserController.findAll2'"))
    @GetMapping("/findAll2")
    public List<UserDTO> findAll2(){
        return userService.findAll();
    }

    @GetMapping("/getById/{id}")
    @Cacheable(cacheNames = "all_user", key = ("'UserController.getById'+ #id"))
    public UserDTO getById(@PathVariable Integer id){
        return userService.getOneById(id);
    }

}