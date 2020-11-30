package com.evol.service.feign;

import com.evol.base.client.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("coach-user-server")
public interface UserClient {

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    List<User> getUsers();

    @RequestMapping(method = RequestMethod.POST, value = "/users/{userId}", consumes = "application/json")
    User update(@PathVariable("userId") Long userId, User user);

}
