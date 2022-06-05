package com.evol.cors.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CorsTestIndexController {
    /**
     * @CrossOrigin 默认允许所有的访问请求，允许客户端所有请求头，预响应最大缓存时间1800s
     * @return
     */
    @CrossOrigin
    //在注解中配置参数，不常用
    //@CrossOrigin(origins = {""}, allowedHeaders = {"GET", "POST"}, exposedHeaders = {""}, methods = {RequestMethod.GET, RequestMethod.POST}, allowCredentials = "cookie", maxAge = 1800)
    @RequestMapping("/corsindex")
    public String index(){
        return "hello man";
    }
}
