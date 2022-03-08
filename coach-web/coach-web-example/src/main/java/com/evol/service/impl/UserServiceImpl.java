package com.evol.service.impl;

import com.evol.domain.dto.StaffDetails;
import com.evol.domain.model.User;
import com.evol.domain.model.UserExample;
import com.evol.mapper.UserMapper;
import com.evol.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(s);
        List<User> list = userMapper.selectByExample(userExample);
        if(list == null || list.get(0) == null){
            return null;
        }

        User user = list.get(0);
        UserDetails userDetails = new StaffDetails(user.getUsername(), user.getPassword());
        return userDetails;
    }

    @Override
    public User addUser(String username, String password) {
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return null;
        }

        BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
        String pwd1 = pwdEncoder.encode(StringUtils.trim(password));
        log.info("pwd1:" + pwd1);

        User user = new User();
        user.setUsername(StringUtils.trim(username));
        user.setPassword(pwd1);
        user.setRoleIds("");
        user.setCreateTime(new Date());
        userMapper.insert(user);
        return user;
    }
}
