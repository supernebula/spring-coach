package com.evol.controller;

import com.evol.config.UserCustomConfig;
import com.evol.domain.RoleVo;
import com.evol.domain.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    private UserCustomConfig userCustomConfig;

    private UserVo userVo;

    @PostConstruct
    public void init(){
        userVo = new UserVo();
        userVo.setRealName("lisi");
        userVo.setAge(10);
        userVo.setTitle("经理");
        userVo.setRemark("备注信息￥%SDG");
        List<RoleVo> roles = new ArrayList<>();
        RoleVo roleVo1 = new RoleVo();
        roleVo1.setName("管理");
        roleVo1.setCode("admin");
        roles.add(roleVo1);
        RoleVo roleVo2 = new RoleVo();
        roleVo2.setName("客服");
        roleVo2.setCode("service");
        roles.add(roleVo2);
        userVo.setRoleList(roles);
    }


    @ResponseBody
    @GetMapping({"/", "/test/index"})
    public String index(){
        return "test docker";
    }


    @GetMapping("/test/propTest")
    public String propTest(){
        return userCustomConfig.getRealName() + "," + userCustomConfig.getAge();
    }

    @GetMapping("/test/propObj")
    public UserVo propObj(){
        return userVo;
    }


    @GetMapping(value = "/test/propJson", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserVo propJson(){
        return userVo;
    }

    @GetMapping(value = "/test/propXml", produces = MediaType.APPLICATION_XML_VALUE)
    public UserVo propXml(){
        return userVo;
    }
}
