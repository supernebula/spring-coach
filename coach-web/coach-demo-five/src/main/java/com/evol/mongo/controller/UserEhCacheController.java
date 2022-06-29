package com.evol.mongo.controller;

import com.evol.mongo.domain.UserDTO;
import com.evol.mongo.service.UserService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CacheConfig(cacheNames = {"testUserCache"})
public class UserEhCacheController {

    @Autowired
    private UserService userService;


    @GetMapping("/userEh/get2/{id}")
    @Cacheable(value = "userCache2", key = "#id")
    public UserDTO getOne2(@PathVariable Integer id){
        return userService.getOneById(id);
    }

    @GetMapping("/userEh/get/{id}")
    @Cacheable( key = "#id")
    public UserDTO getOne(@PathVariable Integer id){
        return userService.getOneById(id);
    }

    @PostMapping("/userEh/save")
    @CachePut(key = "#id")
    public UserDTO save(@RequestBody UserDTO userDTO){
        return userService.insert(userDTO);
    }

    @PostMapping("/userEh/update")
    @CachePut(key = "#userDTO.id")
    public UpdateResult update(@RequestBody UserDTO userDTO){
        return userService.updateResult(userDTO);
    }

    @CacheEvict(key = "#id")
    @DeleteMapping("/userEh/delete/{id}")
    public DeleteResult update(@PathVariable Integer id){
        return userService.deleteResult(id);
    }

    @Cacheable(cacheNames = "all_user", key = ("'UserEhController.findAll2'"))
    @GetMapping("/userEh/findAll2")
    public List<UserDTO> findAll2(){
        return userService.findAll();
    }

    @GetMapping("/userEh/getById/{id}")
    @Cacheable(cacheNames = "all_user", key = ("'UserEhController.getById'+ #id"))
    public UserDTO getById(@PathVariable Integer id){
        return userService.getOneById(id);
    }

}
